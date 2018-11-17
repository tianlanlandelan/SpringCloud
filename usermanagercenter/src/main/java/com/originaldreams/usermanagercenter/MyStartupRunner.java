package com.originaldreams.usermanagercenter;

import com.originaldreams.common.router.MyRouters;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.usermanagercenter.controller.LogonController;
import com.originaldreams.usermanagercenter.controller.RoleController;
import com.originaldreams.usermanagercenter.controller.RouterController;
import com.originaldreams.usermanagercenter.controller.UserController;
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
public class MyStartupRunner  implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(MyStartupRunner.class);

    @Override
    public void run(String... args)
    {
        //初始化路由表
        MyRouters.initRouters(MyServiceName.USER_MANAGER_CENTER,
                LogonController.class,
                RoleController.class,
                RouterController.class,
                UserController.class);
        logger.info("注册路由表");
    }
}
