package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.mybatis.MyBaseMapper;
import com.originaldreams.usermanagercenter.entity.Router;
import com.originaldreams.usermanagercenter.view.PageList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    String allFields = "id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description,createTime";

     @Select("SELECT " + allFields + " FROM " + tableName + " WHERE id = #{id}")
     Router getById(Integer Id);

     @Select({"SELECT a.id,a.name, a.serviceName, a.controllerName, a.methodName, a.routerUrl,a.requestType "
             + " FROM " + tableName + " a ," + roleRouters + " b "
             + " WHERE a.id = b.routerId AND b.roleId = #{roleId}"
     })
     List<Router> getRoutersByRoleId(Integer roleId);

     @Insert("INSERT INTO " + tableName + "(id,name, serviceName, controllerName, methodName, routerUrl,requestType) VALUES (#{id},#{name}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl},#{requestType})")
     Integer insert(Router router);

     @Delete("DELETE FROM " + tableName )
     Integer deleteAll();

}
