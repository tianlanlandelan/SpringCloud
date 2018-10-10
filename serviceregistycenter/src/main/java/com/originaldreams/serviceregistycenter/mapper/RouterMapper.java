package com.originaldreams.serviceregistycenter.mapper;

import com.originaldreams.serviceregistycenter.entity.MyRouterObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RouterMapper {
     String tableName = "router";

     @Select("SELECT id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description FROM " + tableName + " WHERE id = #{id}")
     MyRouterObject getById(Integer Id);

     @Select("SELECT id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description FROM " + tableName)
     List<MyRouterObject> getAll();

     @Insert("INSERT INTO " + tableName + "(id,name, serviceName, controllerName, methodName, routerUrl,requestType,parameters,description) " +
             "VALUES (#{id},#{name}, #{serviceName}, #{controllerName}, #{methodName}, #{routerUrl},#{requestType},#{parameters},#{description})")
     Integer insert(MyRouterObject router);

     @Delete("DELETE FROM " + tableName )
     Integer deleteAll();

}
