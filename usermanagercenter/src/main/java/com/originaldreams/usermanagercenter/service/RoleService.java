package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.mapper.RoleRoutersMapper;
import com.originaldreams.usermanagercenter.mapper.UserRolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.originaldreams.usermanagercenter.entity.Role;
import com.originaldreams.usermanagercenter.mapper.RoleMapper;

import javax.annotation.Resource;

/**
 * @author yangkaile
 * @date 2018-09-05 08:54:10
 */
@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRolesMapper userRolesMapper;

    @Resource
    private RoleRoutersMapper roleRoutersMapper;

    public MyServiceResponse getAll(){
        return new MyServiceResponse(roleMapper.getAll());
    }

    public MyServiceResponse getRoleByUserId(int userId){
        return new MyServiceResponse(roleMapper.getRoleByUserId(userId));
    }

    public MyServiceResponse getRolesByRouterId(int userId){
        return new MyServiceResponse(roleMapper.getRolesByRouterId(userId));
    }

    public MyServiceResponse addRoleForUser(UserRoles userRoles){
        UserRoles result = userRolesMapper.getByUserId(userRoles.getUserId());
        if(result == null){
            return new MyServiceResponse(userRolesMapper.insert(userRoles));
        }
        return new MyServiceResponse(userRolesMapper.update(userRoles));
    }

    public MyServiceResponse deleteById(Integer id){
        userRolesMapper.deleteByRoleId(id);
        roleRoutersMapper.deleteByroleId(id);
        roleMapper.deleteById(id);
        return new MyServiceResponse("已删除");
    }


    public Role getById(Integer id){

        return roleMapper.getById(id);
    }

    public MyServiceResponse insert(Role role){
        Role checker = roleMapper.getByName(role);
        if(checker != null){
            return new MyServiceResponse(MyServiceResponse.SUCCESS_CODE_FAILED,"角色名已存在");
        }
        roleMapper.insert(role);
        return new MyServiceResponse(role.getId());
    }


    public MyServiceResponse update(Role role){
        return new MyServiceResponse(roleMapper.update(role));
    }


}
