package com.originaldreams.logcenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.logcenter.entity.EmailLog;
import com.originaldreams.logcenter.service.EmailLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/emailLog")
public class EmailLogController {
    private Logger logger = LoggerFactory.getLogger(EmailLogController.class);

    @Resource
    private EmailLogService emailLogService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity insert(EmailLog entity){
        return MyResponse.ok(emailLogService.insert(entity));
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseEntity list(EmailLog log,String startDate,String endDate,Integer page_num,Integer page_size) {
        Map<String, Object> params = new HashMap<>();
        MyServiceResponse response = new MyServiceResponse();
        if (page_num != null || page_size != null) {
            Integer offset = (page_num - 1) * page_size;
            params.put("offset", offset);
            params.put("rows", page_size);
        } else {
            params.put("offset", 0);
            params.put("rows", 10);
        }
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("entity", log);
        response.setData(emailLogService.getListByCondition(params));
        return MyResponse.ok(response);
    }


}
