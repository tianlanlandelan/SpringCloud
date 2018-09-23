package com.originaldreams.logcenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.logcenter.entity.LogonLog;
import com.originaldreams.logcenter.service.LogonLogService;
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
 * @author 董晨龙
 * @date 2018-08-15 10:05:40
 */
@RestController
@RequestMapping("/logonLog")
public class LogonLogController {
    private Logger logger = LoggerFactory.getLogger(LogonLogController.class);

    @Resource
    private LogonLogService logonLogService;

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    ResponseEntity insert(LogonLog logonLog){
        MyServiceResponse response =new MyServiceResponse();
        try{
            Integer rows = logonLogService.insert(logonLog);
            response.setSuccess(MyServiceResponse.SUCCESS_CODE_SUCCESS);
            logger.info("新增了登陆日志:"+rows+"条\t id:"+logonLog.getId());
        }catch(Exception e){
            e.printStackTrace();
            return MyResponse.serverError();
        }
        return MyResponse.ok(response);
    }


//    @RequestMapping(value = "/list",method = RequestMethod.GET)
//    public ResponseEntity list(LogonLog log, String startDate, String endDate, Integer page_num, Integer page_size){
//        Map<String,Object> params = new HashMap<>();
//        MyServiceResponse response =new MyServiceResponse();
//        if(page_num!=null||page_size!=null){
//            Integer offset=(page_num-1)*page_size;
//            params.put("offset",offset);
//            params.put("rows",page_size);
//        }else{
//            params.put("offset",0);
//            params.put("rows",10);
//        }
//        params.put("startDate",startDate);
//        params.put("endDate",endDate);
//        params.put("entity",log);
//        response.setData(logonLogService.getListByCondition(params));
//        return MyResponse.ok(response);
//    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    ResponseEntity getById(Integer id){
        LogonLog result = logonLogService.getById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/getByUserId",method = RequestMethod.GET)
    ResponseEntity getByUserId(Integer userId){
        LogonLog result = logonLogService.getByUserId(userId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }


    @RequestMapping(value = "/getByType",method = RequestMethod.GET)
    ResponseEntity getByType(Integer type){
        LogonLog result = logonLogService.getByType(type);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }






    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    ResponseEntity getAll(){
        List<LogonLog> result = logonLogService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }



    @RequestMapping(value = "/deleteById",method = RequestMethod.DELETE)
    ResponseEntity deleteById(Integer id){
        Integer result = logonLogService.deleteById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    ResponseEntity update(LogonLog logonLog){
        Integer result = logonLogService.update(logonLog);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
