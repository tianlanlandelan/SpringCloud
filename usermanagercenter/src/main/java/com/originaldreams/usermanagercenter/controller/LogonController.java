package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.MyUserManagerRouter;
import com.originaldreams.common.router.RouterAttribute;
import com.originaldreams.common.util.StringUtils;
import com.originaldreams.common.util.ValidUserName;
import com.originaldreams.usermanagercenter.entity.User;
import com.originaldreams.usermanagercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录控制
 * 负责用户的登录和注册
 * @author 杨凯乐
 * @date 2018-07-30 09:17:03
 */
@RestController
public class LogonController {
    private Logger logger = LoggerFactory.getLogger(LogonController.class);

    @Resource
    private UserService userService;

    /**
     *
     * @param userName
     * @param phone
     * @param wxId
     * @param email
     * @param password
     * @return
     */
    @RouterAttribute(id = MyUserManagerRouter.LOGON, description = "登录接口")
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public ResponseEntity logon(String userName,String phone,String wxId,String email,String password){
        logger.info(userName + "--" + phone + "--" + email + "--" + password);
        if(userName == null && phone == null && email == null && password == null){
            return MyResponse.badRequest();
        }else{
            User user = new User();
            user.setUserName(userName);
            user.setPhone(phone);
            user.setWxId(wxId);
            user.setEmail(email);
            logger.info("user logon:" + user);
            user.setPassword(password);
            MyServiceResponse response = userService.logon(user);

            return MyResponse.ok(response);
        }
    }

    /**
     * 注册接口
     * @param userName  手机号或邮箱
     * @param password  密码  必填
     * @param verificationCode 验证码
     * @return
     */
    @RouterAttribute(id = MyUserManagerRouter.REGISTER, description = "注册接口，所有参数必填。先通过短信或邮件获取验证码，再调用该接口设置用户名和密码")
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ResponseEntity register(String userName,String password,String verificationCode) {
        try {
            if (StringUtils.isEmpty(userName,password,verificationCode)) {
                return MyResponse.badRequest();
            }
            User user = new User();
            logger.info("user register:" + userName);
            user.setPassword(password);
            if (ValidUserName.isValidPhoneNumber(userName)) {
                user.setPhone(userName);
                user.setPhoneLogon();
                return MyResponse.ok(userService.registerByPhone(user, verificationCode));
            } else if (ValidUserName.isValidEmailAddress(userName)) {
                user.setEmail(userName);
                user.setEmailLogon();
                return MyResponse.ok(userService.registerByEmail(user, verificationCode));
            } else {
                return MyResponse.badRequest();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return MyResponse.serverError();
        }
    }
}
