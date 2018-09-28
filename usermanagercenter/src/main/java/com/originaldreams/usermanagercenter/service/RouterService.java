package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.MyRouters;
import com.originaldreams.common.router.MyRouterObject;
import com.originaldreams.usermanagercenter.cache.MyCache;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.entity.Router;
import com.originaldreams.usermanagercenter.mapper.RoleRoutersMapper;
import com.originaldreams.usermanagercenter.mapper.RouterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkaile 
 * @date 2018-09-28 15:04:58
 */
@Service
public class RouterService {
    @Autowired
    private RouterMapper routerMapper;

    @Autowired
    private RoleRoutersMapper roleRoutersMapper;

    private Logger logger = LoggerFactory.getLogger(RouterService.class);

    /**
     * 初始化路由
     *
     */
    public void initRouters(){
        List<MyRouterObject> list = new ArrayList(MyRouters.routerMap.values());
        routerMapper.deleteAll();
        insert(list);
        logger.trace("initRouters OK");
    }
    private void insert(List<MyRouterObject> list){
        for(MyRouterObject routerObject :list){
            Router router = new Router(routerObject);
            MyCache.routerMap.put(router.getId(),router);
            routerMapper.insert(router);
        }
    }

    public MyServiceResponse getAll(){
        return new MyServiceResponse(routerMapper.getAll());
    }
    public MyServiceResponse getRoutersByRoleId(int roleId){
        return new MyServiceResponse(routerMapper.getRoutersByRoleId(roleId));
    }

    public MyServiceResponse addRouterForRole(RoleRouters roleRouters){
        return new MyServiceResponse(roleRoutersMapper.insert(roleRouters));
    }

    public MyServiceResponse getRouterIdsByUserId(Integer userId){
        List<RoleRouters> list = roleRoutersMapper.getByUserId(userId);
        List<Integer> result = new ArrayList<>();
        for(RoleRouters roleRouters : list){
            result.add(roleRouters.getRouterId());
        }
        return new MyServiceResponse(result);
    }


    public Router getById(Integer id){

        return routerMapper.getById(id);
    }



    public Integer insert(Router router){
        return routerMapper.insert(router);
    }

    public Integer deleteById(Integer id){
        return routerMapper.deleteById(id);
    }

    public Integer update(Router router){
        return routerMapper.update(router);
    }


}
