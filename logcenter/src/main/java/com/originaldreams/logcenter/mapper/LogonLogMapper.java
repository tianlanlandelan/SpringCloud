package com.originaldreams.logcenter.mapper;

import com.originaldreams.logcenter.entity.LogonLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface LogonLogMapper {
    String tableName = "logon_log";


     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName + " WHERE id = #{id}")
     LogonLog getById(Integer Id);


     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName + " WHERE userId = #{userId}")
     LogonLog getByUserId(Integer userId);


     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName + " WHERE type = #{type}")
     LogonLog getByType(Integer type);



     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName)
     List<LogonLog> getAll();

     @Insert("INSERT INTO " + tableName + "(userId, type, way, ip, deviceId, createTime) VALUES (#{userId}, #{type}, #{way}, #{ip}, #{deviceId}, #{createTime})")
     @Options(useGeneratedKeys = true)
     Integer insert(LogonLog logonLog);



     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + tableName + " SET userId=#{userId}, type=#{type}, way=#{way}, ip=#{ip}, deviceId=#{deviceId}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(LogonLog logonLog);

 @Select("<script>" +
         " SELECT * " +
         " FROM " +
         tableName +
         " <where> " +
         "<if test=\"entity != null and entity.type != null\"> AND type= #{entity.type}  </if>" +
         "<if test=\"entity != null and entity.way != null\"> AND way= #{entity.way}  </if>" +
         "<if test=\"entity != null and entity.ip != null\"> AND ip= #{entity.ip}  </if>" +
         "<if test=\"entity != null and entity.userId != null\"> AND userId= #{entity.userId}  </if>" +
         "<if test=\"startDate != null\"> AND createDatetime &gt; #{startDate}  </if>" +
         "<if test=\"endDate != null\"> AND createDatetime  &lt; #{endDate}  </if>" +
         " </where> " +
         " LIMIT  #{offset},#{rows}" +
         "</script>"
 )
 List<LogonLog> getListByCondition(Map params);

 @Update("CREATE TABLE `"+tableName+"_"+"${days}` (" +
         "  `id` int(10) NOT NULL AUTO_INCREMENT," +
         "  `createDatetime` varchar(30) DEFAULT NULL COMMENT '登录时间'," +
         "  `userId` int(10) NOT NULL COMMENT '登录用户id'," +
         "  `type` varchar(10) DEFAULT 'SIGNIN' COMMENT '登录类型'," +
         "  `way` varchar(255) DEFAULT NULL COMMENT '登录方式'," +
         "  `IP` varchar(15) DEFAULT NULL COMMENT '登录时的ip'," +
         "  `deviceId` varchar(255) DEFAULT NULL COMMENT '登录设备'," +
         "  PRIMARY KEY (`id`)" +
         ") ENGINE=InnoDB DEFAULT CHARSET=utf8;")
 Integer createNewTableByDate(@Param("days") String days);
}
