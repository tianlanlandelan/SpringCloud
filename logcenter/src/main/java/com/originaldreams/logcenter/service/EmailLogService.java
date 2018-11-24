package com.originaldreams.logcenter.service;

import com.originaldreams.common.response.ResultData;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.entity.EmailLog;
import com.originaldreams.logcenter.mapper.EmailLogMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-22 15:12:53
 */
@Service
public class EmailLogService {
    @Resource
    private EmailLogMapper emailLogMapper;

    public ResultData insert(EmailLog emailLog){
        emailLogMapper.insert(emailLog);
        return ResultData.success(emailLog.getId());
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
