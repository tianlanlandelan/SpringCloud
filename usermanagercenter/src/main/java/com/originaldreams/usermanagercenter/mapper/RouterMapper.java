package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.common.entity.Router;
import com.originaldreams.common.mybatis.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * RouterMapper
 * @author yangkaile
 * @date 2018-11-29 14:57:27
 */
@Mapper
public interface RouterMapper extends MyBaseMapper {
    String tableName = "router";
    String roleRouters = "role_routers";
     @Select({"SELECT a.id,a.name, a.serviceName, a.controllerName, a.methodName, a.routerUrl,a.requestType "
             + " FROM " + tableName + " a ," + roleRouters + " b "
             + " WHERE a.id = b.routerId AND b.roleId = #{roleId}"
     })
     List<Router> getRoutersByRoleId(Integer roleId);
}
