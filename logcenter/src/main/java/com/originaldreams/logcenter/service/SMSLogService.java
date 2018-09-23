package com.originaldreams.logcenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.logcenter.entity.SMSLog;
import com.originaldreams.logcenter.mapper.SMSLogMapper;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-08-30 09:25:14
 */
@Service
public class SMSLogService {
    @Autowired
    private SMSLogMapper smsLogMapper;

    public MyServiceResponse insert(SMSLog smsLog){
        smsLogMapper.insert(smsLog);
        return new MyServiceResponse(smsLog.getId());
    }

    /**
     * 验证短信验证码并修改状态
     * 当验证码校验成功后，修改验证码状态为已使用
     * 当验证码校验失败后，不修改验证码状态
     * @param phone
     * @param codeStr
     * @return
     */
    public MyServiceResponse checkAndUpdateState(String phone,String codeStr){
        MyServiceResponse response =new MyServiceResponse();
        SMSLog smsLog = smsLogMapper.getByPhone(phone);
        if(smsLog != null && smsLog.getCodeStr() != null && smsLog.getCodeStr().equals(codeStr)){
            smsLogMapper.update(smsLog.getId());
            return response;
        }
        response.setSuccess(MyServiceResponse.SUCCESS_CODE_FAILED);
        response.setMessage("验证码错误");
        return response;
    }


    public MyServiceResponse getByPhone(String phone){
        return new MyServiceResponse(smsLogMapper.getByPhone(phone));
    }


    public SMSLog getById(Integer id){

        return smsLogMapper.getById(id);
    }



    public SMSLog getByType(Integer type){
        return smsLogMapper.getByType(type);
    }


    public List<SMSLog> getAll(){
        return smsLogMapper.getAll();
    }

}
