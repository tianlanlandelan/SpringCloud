package com.originaldreams.usermanagercenter.controller;

import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.router.MyUserManagerRouter;
import com.originaldreams.common.router.RouterAttribute;
import com.originaldreams.usermanagercenter.entity.UserInfo;
import com.originaldreams.usermanagercenter.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 杨凯乐
 * @date 2018-07-30 09:16:35
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    private Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    @RouterAttribute(id = MyUserManagerRouter.GET_USER_INFO_BY_ID, description = "我是登录接口")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity get(Integer userId){
        if(userId == null){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(userInfoService.getById(userId));
    }
    @RouterAttribute(id = MyUserManagerRouter.UPDATE_USER_INFO, description = "我是登录接口")
    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity put(UserInfo userInfo){
        Integer result = userInfoService.update(userInfo);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
