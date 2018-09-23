package com.originaldreams.usermanagercenter;

import com.originaldreams.usermanagercenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 服务启动时需要执行的方法放这里
 */
@Component
public class MyStartupRunner  implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);


    @Resource
    private RouterService routerService;

    @Override
    public void run(String... args)
    {
        //初始化路由表
        routerService.initRouters();
        logger.trace("初始化路由表");
    }
}
