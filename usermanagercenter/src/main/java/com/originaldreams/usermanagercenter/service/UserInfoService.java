package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.mybatis.MyBaseEntity;
import com.originaldreams.common.mybatis.MyBaseUtils;
import com.originaldreams.common.response.ResultData;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.usermanagercenter.view.PageList;
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

    MyBaseEntity baseEntity = MyBaseUtils.getBaseEntity(UserInfo.class);

    public ResultData getById(Integer id){
        if(id == null){
            return ResultData.error("用户ID为空");
        }

        baseEntity.setId(id);
        return ResultData.success(userInfoMapper.baseGetById(baseEntity));
    }

    public ResultData getAll(){
        return ResultData.success(userInfoMapper.baseGetAll(baseEntity));
    }

    public ResultData getPageList(int currentPage,int pageSize){
        return MyBaseUtils.getPageList(currentPage,pageSize,userInfoMapper,baseEntity);
    }

    public Integer deleteById(Integer id){
        return userInfoMapper.deleteById(id);
    }

    public Integer update(UserInfo userInfo){
        return userInfoMapper.update(userInfo);
    }


}
