package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.encryption.MyMD5Utils;
import com.originaldreams.common.response.MyResponseReader;
import com.originaldreams.common.response.ResultData;
import com.originaldreams.common.router.MyLogRouter;
import com.originaldreams.common.router.MyRouters;
import com.originaldreams.common.util.StringUtils;
import com.originaldreams.common.util.ValidUserName;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.mapper.UserInfoMapper;
import com.originaldreams.usermanagercenter.utils.LogonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.mapper.UserMapper;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-08-19 12:02:38
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 登录
     * 判断是否使用密码登录
     *  如果使用密码登录，看是用户名密码、手机号密码、邮箱密码的哪种组合
     *      如果密码校验无误，返回用户ID
     *      如果密码校验失败或不支持指定的密码登录组合，则返回相应的错误信息
     *  如果不是密码登录，看是否是微信登录
     * @param userName
     * @param password
     * @return
     */
    public ResultData logon(String userName,String password)  {
        User user = checkPassword(userName,password);
        if(user == null){
            return ResultData.error("非法访问");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId",user.getId());
        map.put("type",LogonUtils.LOGON_TYPE_LOGON);
        map.put("way", logonWay(userName,user));
        map.put("ip","ddd");
        logger.info("logCenterUrl ：" + MyRouters.getRouterUrl(MyLogRouter.INSERT_LOGON_LOG));
        //记录登录日志
        restTemplate.postForEntity(
                MyRouters.getRouterUrl(MyLogRouter.INSERT_LOGON_LOG) +
                        "?userId={userId}&type={type}&way={way}&ip={ip}",null,String.class,map);
        logger.info("logonLog Ok: Id:"  + user.getId() + ",userName:" + userName);
        user.setPassword("");
        user.setMask(0);
        return ResultData.success(user);
    }
    /**
     *  注册
     * @param userName
     * @param password
     * @param verificationCode
     * @return
     * @throws Exception
     */
    public ResultData register (String userName,String password,String verificationCode) throws Exception{
        User user = checkUserRegistered(userName);
        if(user != null){
            return ResultData.error("用户已存在");
        }
        if (ValidUserName.isValidPhoneNumber(userName)) {
            user.setPhone(userName);
            user.setPhoneLogon();
            //TODO 去logCenter核对短信验证码发送记录
            Map<String, Object> map = new HashMap<>();
            map.put("phone",userName);
            map.put("codeStr",verificationCode);
            //验证短信验证码 TODO
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyRouters.getRouterUrl(MyLogRouter.GET_VERIFICATION_BY_PHONE) +
                            "?phone={phone}&codeStr={codeStr}",String.class,map);
            if(!MyResponseReader.isSuccess(responseEntity)){
                return ResultData.error("验证码错误");
            }
        } else if (ValidUserName.isValidEmailAddress(userName)) {
            user.setEmail(userName);
            user.setEmailLogon();
            //TODO 去logCenter核对邮件发送记录

        } else {
            return ResultData.error("仅支持手机号和邮箱注册");
        }
        user.setPassword(MyMD5Utils.EncoderByMd5(user.getPassword()));
        userMapper.insert(user);
        UserInfo userInfo = new UserInfo(user.getId(),user.getPhone(),user.getEmail());
        userInfoMapper.insert(userInfo);
        return ResultData.success(user.getId());
    }

    /**
     * 检查登录方式
     * @param userName
     * @param user
     * @return
     */
    private int logonWay(String userName,User user){
        if(ValidUserName.isValidPhoneNumber(userName) && user.isPermitPhoneLogon()){
            return LogonUtils.LOGON_WAY_PHONE;
        }
        if(ValidUserName.isValidEmailAddress(userName) && user.isPermitUserNameLogon()){
            return LogonUtils.LOGON_WAY_EMAIL;
        }
        if(user.isPermitUserNameLogon()){
            return LogonUtils.LOGON_WAY_USERNAME;
        }
        return -1;
    }

    /**
     * 校验用户名密码，同时返回该用户数据
     * @param userName
     * @param password
     * @return
     */
    private User checkPassword(String userName,String password){
        User user = checkUserRegistered(userName);
        if(user == null){
            return null;
        }
        try{
            if(MyMD5Utils.checkqual(password,user.getPassword()) && logonWay(userName,user) != -1){
                return user;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * 检验用户名、手机号、邮箱是否注册过
     * @param userName 用户名、手机号、邮箱
     * @return
     */
    public User checkUserRegistered(String userName){
        if(StringUtils.isEmpty(userName)){
            return null;
        }
        if(ValidUserName.isValidPhoneNumber(userName)){
            return userMapper.getByPhone(userName);
        }else if(ValidUserName.isValidEmailAddress(userName)){
            return userMapper.getByEmail(userName);
        }else{
            return userMapper.getByUserName(userName);
        }
    }

    /**
     * 根据角色查找用户
     * @param roleId
     * @return
     */
    public ResultData getUsersByRoleId(int roleId){
        return ResultData.success(userMapper.getUsersByRoleId(roleId));
    }

    public ResultData getAllUserNameAndRoleName(){
        return ResultData.success(userMapper.getAllUserNameAndRoleName());
    }

    public User getById(Integer id){

        return userMapper.getById(id);
    }

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public Integer insert(User user){
        return userMapper.insert(user);
    }

    public Integer deleteById(Integer id){
        return userMapper.deleteById(id);
    }

    public Integer update(User user){
        return userMapper.update(user);
    }


}
