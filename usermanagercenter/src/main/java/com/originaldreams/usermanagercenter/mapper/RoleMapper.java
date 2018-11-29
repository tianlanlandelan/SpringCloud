package com.originaldreams.usermanagercenter.mapper;

import com.originaldreams.common.mybatis.MyBaseMapper;
import com.originaldreams.usermanagercenter.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends MyBaseMapper {
    String tableName = "role";
    String userRoles = "user_roles";
    String roleRouters = "role_routers";

     @Select("SELECT id, name, description, createTime FROM " + tableName + " WHERE name = #{name}")
     Role getByName(Role role);

     @Select("SELECT b.id b, b.name , b.description , b.createTime  "
             +" FROM " + userRoles + " a, "+ tableName + " b "
             + " WHERE a.roleId = b.id AND a.userId = #{userId}" )
     Role getRoleByUserId(Integer userId);

     @Select("SELECT b.id, b.name, b.description, b.createTime "
         +" FROM " + roleRouters + " a, "+ tableName + " b "
         + " WHERE a.roleId = b.id AND a.routerId = #{routerId}" )
     List<Role> getRolesByRouterId(Integer routerId);

     @Insert("INSERT INTO " + tableName + "(name, description, createTime) VALUES (#{name}, #{description}, #{createTime})")
     @Options(useGeneratedKeys = true)
     Integer insert(Role role);

     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);
     @Update("UPDATE " + tableName + " SET name=#{name}, description=#{description}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(Role role);


}
