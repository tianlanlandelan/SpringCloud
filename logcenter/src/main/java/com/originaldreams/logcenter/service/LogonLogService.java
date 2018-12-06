package com.originaldreams.logcenter.service;

import com.originaldreams.common.entity.LogonLog;
import com.originaldreams.common.mybatis.MyBaseEntity;
import com.originaldreams.common.mybatis.MyBaseUtils;
import com.originaldreams.common.response.ResultData;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.mapper.LogonLogMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-22 15:14:14
 */
@Service
public class LogonLogService {
    @Resource
    private LogonLogMapper logonLogMapper;

    MyBaseEntity myBaseEntity = MyBaseUtils.getBaseEntity(LogonLog.class);

    public ResultData insert(LogonLog logonLog){
        return ResultData.success(logonLogMapper.insert(logonLog));
    }


    public ResultData getAll(){
        return ResultData.success(logonLogMapper.baseGetAll(myBaseEntity));
    }

    public ResultData getPageList(int currentPage,int pageSize){
        return MyBaseUtils.getPageList(currentPage,pageSize,logonLogMapper,myBaseEntity);
    }


    public LogonLog getByUserId(Integer userId){
        return logonLogMapper.getByUserId(userId);
    }

    public LogonLog getByType(Integer type){
        return logonLogMapper.getByType(type);
    }
}
