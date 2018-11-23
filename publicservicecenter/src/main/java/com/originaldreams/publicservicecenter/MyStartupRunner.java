package com.originaldreams.publicservicecenter;

import com.originaldreams.common.router.MyRouters;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.publicservicecenter.controller.EmailController;
import com.originaldreams.publicservicecenter.controller.SMSController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * 服务启动时需要执行的方法放这里
 * @author yangkaile
 * @date 2018-10-10 09:54:19
 */
@Component
public class MyStartupRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);

    @Override
    public void run(String... args)
    {
        //初始化路由表
        MyRouters.initRouters(MyServiceName.PUBLIC_SERVICE_CENTER,
                EmailController.class,
                SMSController.class
                );
        logger.info("注册路由表");
    }
}
