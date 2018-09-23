package com.originaldreams.logcenter.entity;

import java.util.Date;

public class User {
     private Integer id;
     private String phone;
     private String password;
     private Date createTime;
     private String type;
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getPhone(){
           return this.phone;
     }
     public void setPhone(String phone){
           this.phone = phone;
     }
     public String getPassword(){
           return this.password;
     }
     public void setPassword(String password){
           this.password = password;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }
     public String getType(){
           return this.type;
     }
     public void setType(String type){
           this.type = type;
     }


@Override
    public String toString() {
        return "User{" +
            "  id:" + id + "  phone:" + phone + "  password:" + password + "  createTime:" + createTime + "  type:" + type + 
        "}";
    }
  }
