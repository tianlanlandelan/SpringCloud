package com.originaldreams.serviceregistycenter.controller;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.response.MyResponse;
import com.originaldreams.serviceregistycenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/routerRegister")
    public ResponseEntity routerRegister(@RequestBody MyRouterObject entity){
        logger.info("routerRegister:" + entity.toString());
        return MyResponse.ok(routerService.insert(entity));
    }

    @RequestMapping("/getRouters")
    public ResponseEntity getRouters(){
        return MyResponse.ok(routerService.getAll());
    }



}
