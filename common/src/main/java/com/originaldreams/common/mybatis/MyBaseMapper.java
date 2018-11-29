package com.originaldreams.common.mybatis;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 一个基础的Mapper，实现通用的增删改查操作，所有Mapper继承该Mapper后，即可使用
 * 每个方法使用的对象T为具体实体类(建议使用MyBaseEntity)：必须包含 allFields(查询字段列表)字段和tableName(表名)字段
 * 提供：
 * 1.根据ID查询功能
 * 2.查询全量列表功能
 * 3.分页查询功能
 * 4.查询总记录数功能
 * @author yangkaile
 * @date 2018-11-28 15:49:09
 */
public interface MyBaseMapper {

    /**
     *
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、id(记录ID)属性的对象
     * @return
     */
    @Select("SELECT #{allFields} FROM #{tableName} WHERE id = #{id}")
    <T> Map<String,Object> baseGetById(T map);


    /**
     * 查询全量列表
     * 使用${}方式解析表名和查询字段列表，避免mybatis解析时将其加上单引号
     * @param map
     * @param <T> 包含allFields(查询字段列表)和tableName(表名)属性的对象
     * @return 返回一个Map对象的List
     */
    @Select("SELECT ${allFields} FROM ${tableName}")
    <T> List<Map<String,Object>> baseGetAll(T map);

    /**
     * 分页查询
     * @param map
     * @param <T> 包含allFields(查询字段列表)、tableName(表名)、startRows(分页查询开始记录行号)、pageSize(页面大小)属性的对象
     * @return
     */
    @Select("SELECT ${allFields} FROM ${tableName} router LIMIT #{startRows},#{pageSize}")
    <T> List<Map<String,Object>> baseGetPageList(T map);

    /**
     * 查询总记录数
     * @param map
     * @param <T> 包含tableName(表名)属性的对象
     * @return
     */
    @Select("SELECT COUNT(1) FROM  ${tableName}")
    <T> Integer baseGetCount(T map);

}
