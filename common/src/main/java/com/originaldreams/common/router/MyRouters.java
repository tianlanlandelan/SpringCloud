package com.originaldreams.common.router;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-09-18 14:51:31
 */
public class MyRouters {
    public static Map<String, MyRouterObject> routerMap = new HashMap<>();
    static {
        MyUserManagerRouter.getInstance().init();
        MyLogRouter.getInstance().init();
        MyPublicServiceRouter.getInstance().init();
    }


    public static void main(String[] arges){
        for(MyRouterObject object :MyRouters.routerMap.values()){
            System.out.println(object);
        }
    }
}
