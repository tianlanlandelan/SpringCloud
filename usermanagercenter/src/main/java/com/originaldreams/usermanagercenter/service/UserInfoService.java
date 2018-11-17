package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.mapper.UserInfoMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-16 21:00:34
 */
@Service
public class UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    public MyServiceResponse getById(Integer id){
        if(id == null){
            return new MyServiceResponse(MyServiceResponse.SUCCESS_CODE_FAILED,"用户ID为空");
        }
        return new MyServiceResponse(userInfoMapper.getById(id));
    }

    public MyServiceResponse getAll(){
        return new MyServiceResponse(userInfoMapper.getAll());
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }


}
