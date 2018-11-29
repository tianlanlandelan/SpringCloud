package com.originaldreams.usermanagercenter.entity;

import com.originaldreams.common.mybatis.FieldAttribute;
import com.originaldreams.common.mybatis.TableAttribute;

import java.util.Date;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-11-29 19:54:04
 */
@TableAttribute("role_routers")
public class RoleRouters {
    /**
    * 角色ID
    */
    @FieldAttribute
     private Integer roleId;
    /**
    * 路由ID
    */
    @FieldAttribute
     private Integer routerId;
    /**
    * 创建时间
    */
    @FieldAttribute
     private Date createTime;
     public RoleRouters(Integer roleId,Integer routerId){
         this.roleId = roleId;
         this.routerId = routerId;
         this.createTime = new Date();
     }
     public Integer getRoleId(){
           return this.roleId;
     }
     public void setRoleId(Integer roleId){
           this.roleId = roleId;
     }
     public Integer getRouterId(){
           return this.routerId;
     }
     public void setRouterId(Integer routerId){
           this.routerId = routerId;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }


@Override
    public String toString() {
        return "RoleRouters{" +
             "  roleId:" + roleId + "  routerId:" + routerId + "  createTime:" + createTime +
        "}";
    }
  }
