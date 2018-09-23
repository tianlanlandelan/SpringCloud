package com.originaldreams.usermanagercenter.entity;

import java.util.Date;
import java.util.List;

public class UserRoles {

    /**
    * 用户ID
    */
     private Integer userId;
    /**
    * 角色ID
    */
     private Integer roleId;
    /**
    * 创建时间
    */
     private Date createTime ;
     public UserRoles(Integer userId,Integer roleId){
         this.userId = userId;
         this.roleId = roleId;
         this.createTime = new Date();
     }
     public Integer getUserId(){
           return this.userId;
     }
     public void setUserId(Integer userId){
           this.userId = userId;
     }
     public Integer getRoleId(){
           return this.roleId;
     }
     public void setRoleId(Integer roleId){
           this.roleId = roleId;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "UserRoles{" +
                "  userId:" + userId + "  roleId:" + roleId + "  createTime:" + createTime +
        "}";
    }
  }
