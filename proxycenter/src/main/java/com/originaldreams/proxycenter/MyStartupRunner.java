package com.originaldreams.proxycenter;

import com.originaldreams.common.entity.Router;
import com.originaldreams.common.router.MyRouters;
import com.originaldreams.proxycenter.cache.CacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


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
        while(true){
            List<Router> list = MyRouters.getRouters();
            CacheUtils.initRouterMap(list);
            try {
                Thread.sleep(30000L);
            }catch (Exception e){
                e.printStackTrace();
                logger.error("initRouters Error :" + e.getMessage());
            }
        }
    }
}
