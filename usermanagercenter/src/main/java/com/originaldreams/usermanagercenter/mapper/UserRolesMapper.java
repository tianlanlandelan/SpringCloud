package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.usermanagercenter.entity.UserRoles;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangkaile
 * @date 2018-08-19 19:43:35
 */
@Mapper
public interface UserRolesMapper {
    String tableName = "user_roles";

     @Select("SELECT userId, roleId, createTime FROM " + tableName + " WHERE userId = #{userId}")
     UserRoles getByUserId(Integer userId);

     @Select("SELECT userId, roleId, createTime FROM " + tableName + " WHERE roleId = #{roleId}")
     UserRoles getByRoleId(Integer roleId);

     @Select("SELECT userId, roleId, createTime FROM " + tableName)
     List<UserRoles> getAll();

     @Insert("INSERT INTO " + tableName + "(userId, roleId, createTime) VALUES (#{userId}, #{roleId}, #{createTime})")
     Integer insert(UserRoles userRoles);

     @Delete("DELETE FROM " + tableName + " WHERE roleId = #{roleId}")
     Integer deleteByRoleId(Integer roleId);

     @Update("UPDATE " + tableName + " SET roleId = #{roleId} , createTime = #{createTime} WHERE userId = #{userId}")
     Integer update(UserRoles userRoles);

}
