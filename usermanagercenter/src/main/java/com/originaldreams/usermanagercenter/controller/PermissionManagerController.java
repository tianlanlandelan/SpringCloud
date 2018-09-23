package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.entity.RoleRouters;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.service.RoleService;
import com.originaldreams.usermanagercenter.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户权限管理控制
 * 提供用户权限相关操作功能（包括角色划分、角色分配、授权等）
 * @author 杨凯乐
 * @date 2018-07-30 09:18:22
 */
@RestController
@RequestMapping("/permissionManager")
public class PermissionManagerController {
    private Logger logger = LoggerFactory.getLogger(PermissionManagerController.class);

    @Resource
    private RoleService roleService;
    @Resource
    private RouterService routerService;

    /**
     * 添加角色
     * @param name
     * @param description
     * @return
     */
    @RequestMapping(value = "addRole" , method = RequestMethod.POST)
    public ResponseEntity addRole(String name,String description){
        if(name == null || description == null){
            return MyResponse.badRequest();
        }
        Role role = new Role(name,description);
        return MyResponse.ok(roleService.insert(role));
    }

    /**
     * 为用户添加角色
     * @param userId
     * @param roleId
     * @return
     */
    @RequestMapping(value = "addRoleForUser" , method = RequestMethod.POST)
    public ResponseEntity addRoleForUser(Integer userId,Integer roleId){
        if(userId == null || roleId == null){
            return MyResponse.badRequest();
        }
        UserRoles userRoles = new UserRoles(userId, roleId);
        return MyResponse.ok(roleService.addRoleForUser(userRoles));
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param routerId
     * @return
     */
    @RequestMapping(value = "addRouterForRole" , method = RequestMethod.POST)
    public ResponseEntity addRouterForRole(Integer roleId,Integer routerId){
        if(roleId == null || routerId == null){
            return MyResponse.badRequest();
        }
        RoleRouters roleRouters = new RoleRouters(roleId,routerId);
        return MyResponse.ok(routerService.addRouterForRole(roleRouters));
    }

    /**
     * 删除角色
     * 这个接口要慎重调用
     * 删除角色的同时，会删除所有用户和权限中与该角色相关的记录
     * @param id    角色id
     * @return
     */
    @RequestMapping(value = "deleteRoleById" ,method = RequestMethod.DELETE)
    public ResponseEntity deleteRoleById(Integer id){
        if(id == null){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(roleService.deleteById(id));
    }

    /**
     * 修改角色
     * @param id    角色Id notNull
     * @param name  新的角色名 notNull
     * @param description 新的角色描述 null
     * @return
     */
    @RequestMapping(value = "updateRole" ,method = RequestMethod.PUT)
    public ResponseEntity updateRole(Integer id,String name,String description){
        if(id == null || name == null){
            return MyResponse.badRequest();
        }
        Role role = new Role(id,name);
        if(description != null){
            role.setDescription(description);
        }
        return MyResponse.ok(roleService.update(role));
    }


}
