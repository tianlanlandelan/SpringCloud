package com.originaldreams.logcenter.entity;

import java.util.Date;
import java.util.List;

public class TableMaintenance {
    /**
    * id
    */
     private Integer id;
    /**
    * 表分类
    */
     private String table_type;
    /**
    * 表创建时间
    */
     private String table_create_day;
    /**
    * 表名
    */
     private String table_name;
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getTable_type(){
           return this.table_type;
     }
     public void setTable_type(String table_type){
           this.table_type = table_type;
     }
     public String getTable_create_day(){
           return this.table_create_day;
     }
     public void setTable_create_day(String table_create_day){
           this.table_create_day = table_create_day;
     }
     public String getTable_name(){
           return this.table_name;
     }
     public void setTable_name(String table_name){
           this.table_name = table_name;
     }


@Override
    public String toString() {
        return "TableMaintenance{" +
            "  id:" + id + "  table_type:" + table_type + "  table_create_day:" + table_create_day + "  table_name:" + table_name + 
        "}";
    }
  }
