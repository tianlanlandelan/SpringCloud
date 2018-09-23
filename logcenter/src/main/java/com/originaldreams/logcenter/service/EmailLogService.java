package com.originaldreams.logcenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.entity.EmailLog;
import com.originaldreams.logcenter.mapper.EmailLogMapper;
import java.util.List;
import java.util.Map;

@Service
public class EmailLogService {
    @Autowired
    private EmailLogMapper emailLogMapper;

    public MyServiceResponse insert(EmailLog emailLog){
        emailLogMapper.insert(emailLog);
        return new MyServiceResponse(emailLog.getId());
    }


    public EmailLog getById(Integer id){

        return emailLogMapper.getById(id);
    }

    public EmailLog getByType(String type){
        return emailLogMapper.getByType(type);
    }

    public EmailLog getByRecipients(String recipients){
        return emailLogMapper.getByRecipients(recipients);
    }




    public List<EmailLog> getAll(){
        return emailLogMapper.getAll();
    }


    public List<EmailLog> getListByCondition(Map params){
        return emailLogMapper.getListByCondition(params);
    }

}
