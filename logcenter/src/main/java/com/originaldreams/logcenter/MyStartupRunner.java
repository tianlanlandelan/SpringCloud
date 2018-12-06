package com.originaldreams.logcenter;

import com.originaldreams.common.router.MyRouters;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.logcenter.controller.EmailLogController;
import com.originaldreams.logcenter.controller.LogonLogController;
import com.originaldreams.logcenter.controller.SMSLogController;
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
        MyRouters.initRouters(MyServiceName.LOG_CENTER,
                EmailLogController.class,
                LogonLogController.class,
                SMSLogController.class
                );
        logger.info("注册路由表");
    }
}
