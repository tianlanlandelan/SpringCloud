package com.originaldreams.usermanagercenter.service;

import com.originaldreams.common.mybatis.MyBaseEntity;
import com.originaldreams.common.mybatis.MyBaseUtils;
import com.originaldreams.common.response.ResultData;
import com.originaldreams.usermanagercenter.entity.UserRoles;
import com.originaldreams.usermanagercenter.mapper.RoleRoutersMapper;
import com.originaldreams.usermanagercenter.mapper.UserRolesMapper;
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

    public ResultData getAll(){
        return ResultData.success(roleMapper.baseGetAll(MyBaseUtils.getBaseEntity(Role.class)));
    }

    public ResultData getRoleByUserId(int userId){
        return ResultData.success(roleMapper.getRoleByUserId(userId));
    }

    public ResultData getRolesByRouterId(int userId){
        return ResultData.success(roleMapper.getRolesByRouterId(userId));
    }

    public ResultData addRoleForUser(UserRoles userRoles){
        UserRoles result = userRolesMapper.getByUserId(userRoles.getUserId());
        if(result == null){
            return ResultData.success(userRolesMapper.insert(userRoles));
        }
        return ResultData.success(userRolesMapper.update(userRoles));
    }

    public ResultData deleteById(Integer id){
        userRolesMapper.deleteByRoleId(id);
        roleRoutersMapper.deleteByroleId(id);
        roleMapper.deleteById(id);
        return ResultData.success("已删除");
    }

    public ResultData insert(Role role){
        Role checker = roleMapper.getByName(role);
        if(checker != null){
            return ResultData.error("角色名已存在");
        }
        roleMapper.insert(role);
        return ResultData.success(role.getId());
    }


    public ResultData update(Role role){
        return ResultData.success(roleMapper.update(role));
    }


}
