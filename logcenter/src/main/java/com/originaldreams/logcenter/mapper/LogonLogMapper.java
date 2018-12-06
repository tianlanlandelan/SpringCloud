package com.originaldreams.logcenter.mapper;

import com.originaldreams.common.entity.LogonLog;
import com.originaldreams.common.mybatis.MyBaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-11-30 14:43:37
 */
@Mapper
public interface LogonLogMapper extends MyBaseMapper {
    String tableName = "logon_log";


     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName + " WHERE userId = #{userId}")
     LogonLog getByUserId(Integer userId);


     @Select("SELECT id, userId, type, way, ip, deviceId, createTime FROM " + tableName + " WHERE type = #{type}")
     LogonLog getByType(Integer type);


     @Insert("INSERT INTO " + tableName + "(userId, type, way, ip, deviceId, createTime) VALUES (#{userId}, #{type}, #{way}, #{ip}, #{deviceId}, #{createTime})")
     @Options(useGeneratedKeys = true)
     Integer insert(LogonLog logonLog);


     @Update("UPDATE " + tableName + " SET userId=#{userId}, type=#{type}, way=#{way}, ip=#{ip}, deviceId=#{deviceId}, createTime=#{createTime} WHERE id = #{id}")
     Integer update(LogonLog logonLog);
}
