package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.mybatis.MyBaseEntity;
import com.originaldreams.common.mybatis.MyBaseUtils;
import com.originaldreams.common.response.ResultData;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.entity.Router;
import com.originaldreams.usermanagercenter.mapper.*;
import com.originaldreams.usermanagercenter.view.PageList;
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
        return ResultData.success(routerMapper.baseGetAll(MyBaseUtils.getBaseEntity(Router.class)));
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

    /**
     * 分页查询
     * 只有在查询第一页数据时会返回记录总条数
     * @param currentPage   当前页码 不能小于0
     * @param pageSize      每页数据条数 不能小于0 不能超过100
     * @return
     */
    public ResultData getPageList(int currentPage,int pageSize){
        if(currentPage < 1 || pageSize < 0 || pageSize > ConfigUtils.MAX_PAGE_SIZE){
            return ResultData.error("非法数据");
        }else {
            MyBaseEntity baseEntity = MyBaseUtils.getBaseEntity(Router.class);
            PageList pageList = new PageList();
            //查询第一页数据时返回记录总条数
            if(currentPage == 1){
                pageList.setTotal(routerMapper.baseGetCount(baseEntity));
            }

            baseEntity.setPageSize(pageSize);
            baseEntity.setStartRows((currentPage - 1) * pageSize);
            pageList.setData(routerMapper.baseGetPageList(baseEntity));
            return ResultData.success(pageList);
        }
    }
}
