package com.originaldreams.logcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.entity.LogonLog;
import com.originaldreams.logcenter.mapper.LogonLogMapper;
import java.util.List;

@Service
public class LogonLogService {
    @Autowired
    private LogonLogMapper logonLogMapper;

    public Integer insert(LogonLog logonLog){
        return logonLogMapper.insert(logonLog);
    }

    public LogonLog getById(Integer id){

        return logonLogMapper.getById(id);
    }

    public LogonLog getByUserId(Integer userId){
        return logonLogMapper.getByUserId(userId);
    }

    public LogonLog getByType(Integer type){
        return logonLogMapper.getByType(type);
    }




    public List<LogonLog> getAll(){
        return logonLogMapper.getAll();
    }



    public Integer deleteById(Integer id){
        return logonLogMapper.deleteById(id);
    }

    public Integer update(LogonLog logonLog){
        return logonLogMapper.update(logonLog);
    }


}
