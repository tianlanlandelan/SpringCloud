package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.Router;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface RouterMapper {
    String tableName = "router";
    String roleRouters = "role_routers";

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod FROM " + tableName + " WHERE id = #{id}")
     Router getById(Integer Id);

     @Select("SELECT id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod FROM " + tableName)
     List<Router> getAll();

     @Select({"SELECT a.id, a.serviceName, a.controllerName, a.methodName, a.routerUrl, a.firstMask, a.secondMask,a.requestMethod "
             + " FROM " + tableName + " a ," + roleRouters + " b "
             + " WHERE a.id = b.routerId AND b.roleId = #{roleId}"
     })
     List<Router> getRoutersByRoleId(Integer roleId);

     @Insert("INSERT INTO " + tableName + "(id, serviceName, controllerName, methodName, routerUrl, firstMask, secondMask,requestMethod) VALUES (#{id}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl}, #{firstMask}, #{secondMask},#{requestMethod})")
     Integer insert(Router router);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + tableName + " SET serviceName=#{serviceName}, controllerName=#{controllerName}, methodName=#{methodName}, routerUrl=#{routerUrl}, firstMask=#{firstMask}, secondMask=#{secondMask} WHERE id = #{id}")
     Integer update(Router router);

     @Delete("DELETE FROM " + tableName )
     Integer deleteAll();

}
