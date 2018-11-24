package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.ResultData;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.entity.Router;
import com.originaldreams.usermanagercenter.mapper.RoleRoutersMapper;
import com.originaldreams.usermanagercenter.mapper.RouterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-09-28 15:04:58
 */
@Service
public class RouterService {
    @Resource
    private RouterMapper routerMapper;

    @Resource
    private RoleRoutersMapper roleRoutersMapper;

    private Logger logger = LoggerFactory.getLogger(RouterService.class);


    public ResultData getAll(){
        return ResultData.success(routerMapper.getAll());
    }
    public ResultData getRoutersByRoleId(int roleId){
        return ResultData.success(routerMapper.getRoutersByRoleId(roleId));
    }

    public ResultData addRouterForRole(RoleRouters roleRouters){
        return ResultData.success(roleRoutersMapper.insert(roleRouters));
    }

    public ResultData getRouterIdsByUserId(Integer userId){
        List<RoleRouters> list = roleRoutersMapper.getByUserId(userId);
        List<Integer> result = new ArrayList<>();
        for(RoleRouters roleRouters : list){
            result.add(roleRouters.getRouterId());
        }
        return ResultData.success(result);
    }


    public Router getById(Integer id){

        return routerMapper.getById(id);
    }



    public Integer insert(Router router){
        return routerMapper.insert(router);
    }



}
