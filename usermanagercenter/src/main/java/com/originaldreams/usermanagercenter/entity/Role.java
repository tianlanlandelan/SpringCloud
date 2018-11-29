package com.originaldreams.usermanagercenter.entity;

import com.originaldreams.common.mybatis.FieldAttribute;
import com.originaldreams.common.mybatis.TableAttribute;

import java.util.Date;
import java.util.List;

/**
 * @author  杨凯乐
 * @date 2018-08-11 13:30:33
 */
@TableAttribute("role")
public class Role {
    /**
    * 角色ID
    */
    @FieldAttribute
     private Integer id;
    /**
    * 角色名称
    */
    @FieldAttribute
     private String name;
    /**
    * 角色描述
    */
    @FieldAttribute
     private String description;
    /**
    * 创建时间
    */
    @FieldAttribute
     private Date createTime;
     public Role(){

     }

     public Role(Integer id,String name){
         this.id = id;
         this.name = name;
         this.createTime = new Date();
     }

     public Role(String name,String description){
         this.name = name;
         this.description = description;
         this.createTime = new Date();
     }
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getName(){
           return this.name;
     }
     public void setName(String name){
           this.name = name;
     }
     public String getDescription(){
           return this.description;
     }
     public void setDescription(String description){
           this.description = description;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "Role{" +
            "  id:" + id + "  name:" + name + "  description:" + description + "  createTime:" + createTime + 
        "}";
    }
  }
