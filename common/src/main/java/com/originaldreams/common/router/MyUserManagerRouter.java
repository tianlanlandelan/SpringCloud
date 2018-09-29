package com.originaldreams.common.router;

import com.originaldreams.common.util.ConfigUtils;

/**
 * @author yangkaile
 * @date 2018-09-18 13:46:58
 */
public class MyUserManagerRouter  extends MyRouter {

    @Override
    public int getServiceRouterId(){
        return ConfigUtils.ROUTER_ID_USER_MANAGER;
    }

    @Override
    public String getServiceName(){
        return MyServiceName.USER_MANAGER_CENTER;
    }

    private static MyUserManagerRouter instance;
    public static MyUserManagerRouter getInstance(){
        if(instance == null){
            instance = new MyUserManagerRouter();
        }
        return instance;
    }

    @Override
    public  void init(){
        addRouter(USER_MANAGER_LOGON);
        addRouter(USER_MANAGER_REGISTER);
        addRouter(USER_MANAGER_USER_INFO_GET);
        addRouter(USER_MANAGER_PERMISSION_GET_ALL_ROUTERS);
        addRouter(USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME);
        addRouter(USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID);
        addRouter(USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID);
        addRouter(USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE);
        addRouter(USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER);
        addRouter(USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID);
        addRouter(USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID);
        addRouter(USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE);
        addRouter(USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID);
        addRouter(USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID);
        addRouter(USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE);
    }


    /**
     * 登录
     * POST
     * userName:String null
     * phone:String null
     * email:String null
     * password:String notNull
     */
    public final MyRouterObject USER_MANAGER_LOGON =
            new MyRouterObject(getRouterId(1),
                    "USER_MANAGER_LOGON",
                    getUrl( "/logon"),
                    ConfigUtils.REQUEST_METHOD_GET);


    /**
     * 注册
     * POST
     * userName:String null
     * phone:String null
     * email:String null
     * password:String notNull
     */
    public final MyRouterObject USER_MANAGER_REGISTER =
            new MyRouterObject(getRouterId(2),
                    "USER_MANAGER_REGISTER",
                    getUrl( "/register"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /*
     *用户权限访问
     */

    /**
     * 查询用户的角色
     * GET
     * userId:Integer notNull
     *
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID =
            new MyRouterObject(getRouterId(3),
                    "USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID",
                    getUrl("/permission/getRoleByUserId"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 查询包含某个权限的角色
     * GET
     * routerId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID =
            new MyRouterObject(getRouterId(4),
                    "USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID",
                    getUrl( "/permission/getRolesByRouterId"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 查询拥有某个角色的用户
     * GET
     * roleId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID =
            new MyRouterObject(getRouterId(5),
                    "USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID",
                    getUrl( "/permission/getUsersByRoleId"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 查询所有权限
     * GET
     * null
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ALL_ROUTERS =
            new MyRouterObject(getRouterId(6),
                    "USER_MANAGER_PERMISSION_GET_ALL_ROUTERS",
                    getUrl( "/permission/getAllRouters"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 查询某个角色拥有的权限
     * GET
     * roleId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID =
            new MyRouterObject(getRouterId(7),
                    "USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID",
                    getUrl("/permission/getRoutersByRoleId")
                    ,ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 查询某个用户拥有的权限ID
     * GET
     * userId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID =
            new MyRouterObject(getRouterId(8),
                    "USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID",
                    getUrl( "/permission/getRouterIdsByUserId"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /*
     * 用户权限管理
     */
    /**
     * 添加角色
     * POST
     * name:String notNull
     * description:String notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE =
            new MyRouterObject(getRouterId(9),
                    "USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE",
                    getUrl("/permissionManager/addRole"),
                    ConfigUtils.REQUEST_METHOD_POST);

    /**
     * 添加用户的角色
     * POST
     * userId:Integer notNull
     * roleId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER =
            new MyRouterObject(getRouterId(10),
                    "USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER",
                    getUrl( "/permissionManager/addRoleForUser"),
                    ConfigUtils.REQUEST_METHOD_POST);

    /**
     * 添加角色的权限
     * POST
     * roleId:Integer notNull
     * routerId:Integer notNull
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE =
            new MyRouterObject(getRouterId(11),
                    "USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE",
                    getUrl( "/permissionManager/addRouterForRole"),
                    ConfigUtils.REQUEST_METHOD_POST);

    /**
     * 删除角色
     * DELETE
     * id:Integer notNull
     */
    public final  MyRouterObject USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID =
            new MyRouterObject(getRouterId(12),
                    "USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID",
                    getUrl( "/permissionManager/deleteRoleById"),
                    ConfigUtils.REQUEST_METHOD_DELETE);

    /**
     * 修改角色
     * PUT
     * id:Integer notNull
     * name:String notNull
     * description:String null
     */
    public final MyRouterObject USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE =
            new MyRouterObject(getRouterId(13),
                    "USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE",
                    getUrl("/permissionManager/updateRole"),
                    ConfigUtils.REQUEST_METHOD_PUT);


    public final MyRouterObject USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME =
            new MyRouterObject(getRouterId(14),
                    "USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME",
                    getUrl( "/permission/getAllUserNameAndRoleName"),
                    ConfigUtils.REQUEST_METHOD_GET);


    public final MyRouterObject USER_MANAGER_USER_INFO_GET =
            new MyRouterObject(getRouterId(15),
                    "USER_MANAGER_USER_INFO_GET",
                    getUrl( "/userInfo/get"),
                    ConfigUtils.REQUEST_METHOD_GET);


}
