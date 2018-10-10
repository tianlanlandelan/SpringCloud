package com.originaldreams.serviceregistycenter.controller;

import com.originaldreams.serviceregistycenter.entity.MyRouterObject;
import com.originaldreams.serviceregistycenter.service.RouterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class RouterController {

    @Resource
    RouterService routerService;

    @RequestMapping("/test")
    public void test(Map<Integer,MyRouterObject> entity){
        System.out.println(entity);
    }


}
