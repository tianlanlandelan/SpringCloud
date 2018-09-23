package com.originaldreams.logcenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.util.ValidUserName;
import com.originaldreams.logcenter.entity.SMSLog;
import com.originaldreams.logcenter.service.SMSLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-08-28 21:23:52
 */
@RestController
@RequestMapping("/smsLog")
public class SMSLogController {
    private Logger logger = LoggerFactory.getLogger(SMSLogController.class);

    @Resource
    private SMSLogService smsLogService;

    /**
     * 添加短信验证码发送记录
     * 在PublicServiceCenter组件向用户发送短信验证码后调用该接口记录日志
     * @param smsLog
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    ResponseEntity insert(SMSLog smsLog){
        return MyResponse.ok(smsLogService.insert(smsLog));
    }

    /**
     * 验证短信验证码是否正确
     * @param phone 手机号
     * @param codeStr 验证码
     * @return
     */
    @RequestMapping(value = "/checkAndUpdateState",method = RequestMethod.GET)
    ResponseEntity checkAndUpdateState(String phone,String codeStr){
        if(phone == null || phone.isEmpty() || codeStr == null || codeStr.isEmpty() || !ValidUserName.isValidPhoneNumber(phone)){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(smsLogService.checkAndUpdateState(phone,codeStr));
    }



    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    ResponseEntity getById(Integer id){
        SMSLog result = smsLogService.getById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }


    @RequestMapping(value = "/getByType",method = RequestMethod.GET)
    ResponseEntity getByType(Integer type){
        SMSLog result = smsLogService.getByType(type);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }


    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    ResponseEntity getAll(){
        List<SMSLog> result = smsLogService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
