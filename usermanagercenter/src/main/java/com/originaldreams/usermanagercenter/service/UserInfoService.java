package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.mapper.UserInfoMapper;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;



    public MyServiceResponse getById(Integer id){
        if(id == null){
            return new MyServiceResponse(MyServiceResponse.SUCCESS_CODE_FAILED,"用户ID为空");
        }
        return new MyServiceResponse(userInfoMapper.getById(id));
    }



    public List<UserInfo> getAll(){
        return userInfoMapper.getAll();
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }


}
