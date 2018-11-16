package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.router.MyUserManagerRouter;
import com.originaldreams.common.router.RouterAttribute;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.service.UserInfoService;
import com.originaldreams.usermanagercenter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 杨凯乐
 * @date 2018-07-30 09:16:35
 */
@RestController
@RequestMapping("/userInfo")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @RouterAttribute(id = MyUserManagerRouter.GET_USER_INFO_BY_ID, description = "获取用户信息")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity get(Integer userId){
        if(userId == null){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.getById(userId));
    }
    @RouterAttribute(id = MyUserManagerRouter.UPDATE_USER_INFO, description = "修改用户信息")
    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity put(UserInfo userInfo){
        Integer result = userInfoService.update(userInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }


    /**
     * 查询拥有某个角色的用户
     * @param roleId
     * @return
     */
    @RouterAttribute(id = MyUserManagerRouter.GET_USER_BY_ROLE_ID, description = "查询拥有某角色的用户")
    @RequestMapping(value = "/getUsersByRoleId" , method = RequestMethod.GET)
    public ResponseEntity getUsersByRoleId(Integer roleId){
        if(roleId == null || roleId < 0){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userService.getUsersByRoleId(roleId));
    }


    @RouterAttribute(id = MyUserManagerRouter.GET_ALL_USER_NAME_AND_ROLE_NAME, description = "获取所有用户和对应的角色")
    @RequestMapping(value = "/getAllUserNameAndRoleName" ,method = RequestMethod.GET)
    public ResponseEntity getAllUserNameAndRoleName(){
        return MyResponse.ok(userService.getAllUserNameAndRoleName());
    }

}
