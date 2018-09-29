package com.originaldreams.common.router;

import com.originaldreams.common.util.ConfigUtils;

/**
 * 维护组件为前端提供的接口，这些接口会自动读取到my_core库的router表中
 * 接口命名规则：组件名_Controller上的RequestMapping.value_接口上的RequestMapping.value  首字母大写
 * 新增的接口在这里注册，同时加入到routerMap里
 * @author 杨凯乐
 * @date 2018-07-30 09:25:42
 */
public abstract class MyRouter {

    /**
     * 获取组件名
     * @return 组件名 如： UserManagerCenter
     */
    public abstract String getServiceName();

    /**
     * 通过组件内路由获取到完整的routerUrl
     * @param router 组件内的路由 如 /http/post
     * @return 如 http://LogCenter/http/post
     */
    public  String getUrl(String router){
        return ConfigUtils.HTTP_UTL_PREFIX + getServiceName() + router;
    }

    /**
     * 获取组件的路由ID
     * @return 组件路由ID 如 10
     */
    public abstract int getServiceRouterId();

    public Integer getRouterId(int number) throws RuntimeException{
        if(number >= ConfigUtils.SERVICE_ROUTER_MAX){
            throw new RuntimeException("超过预设的路由上限");
        }
        return getServiceRouterId() * ConfigUtils.SERVICE_ROUTER_MAX + number;
    }

    /**
     * 初始化路由表，在这个方法里依次调用initRouter方法，将所有的路由加载进来
     */
    public abstract void init();

    /**
     * 加载路由
     * 将一个路由对象加载到路由表中
     * @param router 路由对象
     */
    public  void addRouter(MyRouterObject router){
        MyRouters.routerMap.put(router.getName(),router);
    }

}
