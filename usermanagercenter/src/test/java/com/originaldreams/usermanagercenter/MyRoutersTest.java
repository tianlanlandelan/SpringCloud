package com.originaldreams.usermanagercenter;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.usermanagercenter.controller.LogonController;
import com.originaldreams.usermanagercenter.controller.PermissionController;
import com.originaldreams.usermanagercenter.controller.PermissionManagerController;
import com.originaldreams.usermanagercenter.controller.UserInfoController;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class MyRoutersTest {
    @Test
    public  void readRouters() {

        List<MyRouterObject> list = MyRouters111.getInstance().initRouters(MyServiceName.USER_MANAGER_CENTER,
                LogonController.class,
                PermissionController.class,
                PermissionManagerController.class,
                UserInfoController.class);
        RestTemplate restTemplate = new RestTemplate();

    }
}
