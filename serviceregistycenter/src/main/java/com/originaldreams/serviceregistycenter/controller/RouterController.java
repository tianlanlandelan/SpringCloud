package com.originaldreams.serviceregistycenter.controller;

import com.originaldreams.common.entity.Router;
import com.originaldreams.common.response.MyResponse;
import com.originaldreams.serviceregistycenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2018-10-11 09:06:32
 */
@RestController
public class RouterController {
    private Logger logger = LoggerFactory.getLogger(RouterController.class);

    @Resource
    RouterService routerService;

    @RequestMapping(value = "/routerRegister",method = RequestMethod.POST)
    public ResponseEntity routerRegister(@RequestBody Router entity){
        logger.info("routerRegister:" + entity.toString());
        return MyResponse.ok(routerService.insert(entity));
    }


    @RequestMapping(value = "/cleanByServiceName",method = RequestMethod.GET)
    public ResponseEntity cleanByServiceName(String serviceName){
        logger.info("cleanByServiceName : " + serviceName);
        return MyResponse.ok(routerService.deleteByServiceName(serviceName));
    }

    @RequestMapping(value = "/getRouters",method = RequestMethod.GET)
    public ResponseEntity getRouters(){
        return MyResponse.ok(routerService.getAll());
    }



}
