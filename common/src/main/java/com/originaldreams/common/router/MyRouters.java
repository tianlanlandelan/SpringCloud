package com.originaldreams.common.router;

import com.originaldreams.common.entity.MyRouterObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-10-09 09:37:33
 */
public class MyRouters {
    private static MyRouters _instance;
    public static MyRouters getInstance(){
        if(_instance == null){
            _instance  = new MyRouters();
        }
        return _instance;
    }
    public  Map<Integer,MyRouterObject> routerMap = new HashMap();
    public void initRouterMap(List<MyRouterObject> list){
        for (MyRouterObject myRouterObject:list){
            routerMap.put(myRouterObject.getId(),myRouterObject);
        }
    }
}
