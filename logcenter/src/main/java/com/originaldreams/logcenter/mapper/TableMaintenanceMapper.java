package com.originaldreams.logcenter.mapper;

import com.originaldreams.logcenter.entity.TableMaintenance;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface TableMaintenanceMapper {
    String tableName = "table_maintenance";


     @Select("SELECT id, table_type, table_create_day, table_name FROM " + tableName + " WHERE id = #{id}")
     TableMaintenance getById(Integer Id);

     @Select("SELECT * FROM " + tableName +" WHERE table_type=#{table_type} ORDER BY table_create_day DESC LIMIT 1")
     TableMaintenance getLastestTable(Map params);

     @Select("SELECT id, table_type, table_create_day, table_name FROM " + tableName)
     List<TableMaintenance> getAll();

     @Insert("INSERT INTO " + tableName + "(table_type, table_create_day, table_name) VALUES (#{table_type}, #{table_create_day}, #{table_name})")
     @Options(useGeneratedKeys = true)
     Integer insert(TableMaintenance tableMaintenance);



     @Delete("DELETE FROM " + tableName + " WHERE id = #{id}")
     Integer deleteById(Integer id);

     @Update("UPDATE " + tableName + " SET table_type=#{table_type}, table_create_day=#{table_create_day}, table_name=#{table_name} WHERE id = #{id}")
     Integer update(TableMaintenance tableMaintenance);


}
