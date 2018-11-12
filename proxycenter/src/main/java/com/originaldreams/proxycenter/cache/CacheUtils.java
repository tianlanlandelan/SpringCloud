package com.originaldreams.proxycenter.cache;

import com.originaldreams.common.entity.MyRouterObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-07 09:23:00
 */
public class CacheUtils {
    private static Map<String,MyRouterObject> routerMap = new HashMap<>();
    public static Map<Integer,List<Integer>> userRouterMap = new HashMap<>();
    public static Map<Integer,String> userRoleMap = new HashMap<>();

    private static String lock = "lock";
    public static Map<String,MyRouterObject> getRouterMap(){
        synchronized (lock){
            return routerMap;
        }
    }

    public static void initRouterMap(List<MyRouterObject> list){
        synchronized (lock){
            routerMap.clear();
            for(MyRouterObject object:list){
                routerMap.put(object.getName(),object);
            }
        }
    }
}
