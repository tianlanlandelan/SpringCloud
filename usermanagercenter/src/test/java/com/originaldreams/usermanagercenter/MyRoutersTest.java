package com.originaldreams.usermanagercenter;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.usermanagercenter.controller.LogonController;
import com.originaldreams.usermanagercenter.controller.PermissionController;
import com.originaldreams.usermanagercenter.controller.PermissionManagerController;
import com.originaldreams.usermanagercenter.controller.UserInfoController;
import org.junit.Test;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRoutersTest {
    @Test
    public  void readRouters() {

        List<MyRouterObject> list = MyRouters111.getInstance().initRouters(MyServiceName.USER_MANAGER_CENTER,
                LogonController.class,
                PermissionController.class,
                PermissionManagerController.class,
                UserInfoController.class);
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));
//        for(MyRouterObject object:list){
//            String responseEntity = restTemplate.postForObject("http://127.0.0.1:8801/test", object, String.class);
//            System.out.println("registerRouters:" + responseEntity);
//        }
    }
}
