package com.originaldreams.common.router;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-09-18 14:51:31
 */
public class Routers {
    public static Map<String, MyRouterObject> routerMap = new HashMap<>();
    public Routers(){
        MyUserManagerRouter.getInstance().init();
        MyLogRouter.getInstance().init();
        MyPublicServiceRouter.getInstance().init();

    }


    public static void main(String[] arges){
        Routers routers = new Routers();
        for(MyRouterObject object :Routers.routerMap.values()){
            System.out.println(object);
        }
    }
}
