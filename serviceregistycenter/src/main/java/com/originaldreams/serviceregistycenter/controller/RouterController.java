package com.originaldreams.serviceregistycenter.controller;

import com.originaldreams.common.router.MyRouterObject;
import com.originaldreams.serviceregistycenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RouterController {
    private Logger logger = LoggerFactory.getLogger(RouterController.class);

    @Resource
    RouterService routerService;

    @RequestMapping("/routerRegister")
    public void test(@RequestBody MyRouterObject entity){
        logger.error("routerRegister:" + entity.toString());
    }


}
