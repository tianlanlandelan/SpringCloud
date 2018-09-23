package com.originaldreams.common.router;

import java.util.HashMap;
import java.util.Map;

/**
 * 维护组件为前端提供的接口，这些接口会自动读取到my_core库的router表中
 * 接口命名规则：组件名_Controller上的RequestMapping.value_接口上的RequestMapping.value  首字母大写
 * 新增的接口在这里注册，同时加入到routerMap里
 * @author 杨凯乐
 * @date 2018-07-30 09:25:42
 */
public class MyRouter {
    public final static String PREFIX = "http://";

    public final static String REQUEST_METHOD_GET = "GET";
    public final static String REQUEST_METHOD_POST = "POST";
    public final static String REQUEST_METHOD_DELETE = "DELETE";
    public final static String REQUEST_METHOD_PUT = "PUT";


    /*
     * 测试
     */
    /**
     * LogCenter提供的测试http接口
     * GET
     * id: Integer，null
     * name: String，null
     */
    public final static String LOG_HTTP_GET = PREFIX + MyServiceName.LOG_CENTER + "/http/get";

    /**
     * LogCenter提供的测试http接口
     * POST
     * id: Integer，notNull
     * name: String，notNull
     */
    public final static String LOG_HTTP_POST = PREFIX + MyServiceName.LOG_CENTER + "/http/post";


    /**
     * 验证短信验证码是否正确
     * GET
     * phone 手机号 notNull
     * codeStr 验证码 notNull
     */
    public final static String LOG_SMSLOG_CHECK_AND_UPDATE_STATE =  PREFIX + MyServiceName.LOG_CENTER + "/smsLog/checkAndUpdateState";

    /**
     * 登录
     * POST
     * userName:String null
     * phone:String null
     * email:String null
     * password:String notNull
     */
    public final static String USER_MANAGER_LOGON = PREFIX + MyServiceName.USER_MANAGER_CENTER + "/logon";

    /**
     * 注册
     * POST
     * userName:String null
     * phone:String null
     * email:String null
     * password:String notNull
     */
    public final static String USER_MANAGER_REGISTER = PREFIX + MyServiceName.USER_MANAGER_CENTER + "/register";

    /**
     * 发送短信验证码
     * GER
     * phone:String notNull
     */
    public final static String PUBLIC_SERVICE_SMS_SEND_VERIFICATIONCODE = PREFIX + MyServiceName.PUBLIC_SERVICE_CENTER + "/SMS/sendVerificationCode";

    /*
     *用户权限访问
     */
    /**
     * 查询所有角色
     * GET
     * null
     */
    public final static String USER_MANAGER_PERMISSION_GET_ALL_ROLES =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getAllRoles";
    /**
     * 查询用户的角色
     * GET
     * userId:Integer notNull
     *
     */
    public final static String USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getRoleByUserId";
    /**
     * 查询包含某个权限的角色
     * GET
     * routerId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getRolesByRouterId";
    /**
     * 查询拥有某个角色的用户
     * GET
     * roleId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getUsersByRoleId";
    /**
     * 查询所有权限
     * GET
     * null
     */
    public final static String USER_MANAGER_PERMISSION_GET_ALL_ROUTERS =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getAllRouters";
    /**
     * 查询某个角色拥有的权限
     * GET
     * roleId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getRoutersByRoleId";

    /**
     * 查询某个用户拥有的权限ID
     * GET
     * userId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getRouterIdsByUserId";


    /*
     * 用户权限管理
     */
    /**
     * 添加角色
     * POST
     * name:String notNull
     * description:String notNull
     */
    public final static String USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permissionManager/addRole";
    /**
     * 添加用户的角色
     * POST
     * userId:Integer notNull
     * roleId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permissionManager/addRoleForUser";
    /**
     * 添加角色的权限
     * POST
     * roleId:Integer notNull
     * routerId:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permissionManager/addRouterForRole";

    /**
     * 删除角色
     * DELETE
     * id:Integer notNull
     */
    public final static String USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permissionManager/deleteRoleById";

    /**
     * 修改角色
     * PUT
     * id:Integer notNull
     * name:String notNull
     * description:String null
     */
    public final static String USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permissionManager/updateRole";

    public final static String USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/permission/getAllUserNameAndRoleName";

    public final static String USER_MANAGER_USER_INFO_GET =
            PREFIX + MyServiceName.USER_MANAGER_CENTER + "/userInfo/get";

    /**
     * 添加用户登录日志
     * POST
     * userId:Integer notNull
     * type:Integer notNull
     * way:Integer notNull
     * ip:String null
     * deviceId:String null
     */
    public final static String LOG_LOGON_LOG_INSERT = PREFIX + MyServiceName.LOG_CENTER + "/logonLog/insert";
    /**
     * 添加短信发送日志
     * POST
     * phone:String notNull
     * type:Integer notNull
     * templateId:String notNull
     * codeStr:String notNull
     * minuteStr:String notNull
     * result:String notNull
     * stateCode:String notNull
     */
    public final static String LOG_SMS_LOG_INSERT = PREFIX + MyServiceName.LOG_CENTER + "/smsLog/insert";

    /**
     * 保存所有的权限
     * Key:MethodName 客户端访问所有权限都要根据方法名访问
     * Value:权限
     */
    public  static Map<String, MyRouterObject> routerMapGet = new HashMap<>();
    public  static Map<String, MyRouterObject> routerMapPost = new HashMap<>();
    public  static Map<String, MyRouterObject> routerMapDelete = new HashMap<>();
    public  static Map<String, MyRouterObject> routerMapPut = new HashMap<>();

    /**
     * 初始化routerMap
     * 这个在添加新的router时要仔细检查，服务启动时会根据routerMap来初始化路由表
     */
    static{
        routerMapGet.put("LOG_HTTP_GET",
                new MyRouterObject(10000, LOG_HTTP_GET));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ALL_ROLES",
                new MyRouterObject(10001, USER_MANAGER_PERMISSION_GET_ALL_ROLES));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID",
                new MyRouterObject(10002, USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID",
                new MyRouterObject(10003, USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID",
                new MyRouterObject(10004, USER_MANAGER_PERMISSION_GET_USERS_BY_ROLE_ID));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ALL_ROUTERS",
                new MyRouterObject(10005, USER_MANAGER_PERMISSION_GET_ALL_ROUTERS));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID",
                new MyRouterObject(10006, USER_MANAGER_PERMISSION_GET_ROUTERS_BY_ROLE_ID));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID",
                new MyRouterObject(10007, USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID));
        routerMapGet.put("USER_MANAGER_USER_INFO_GET",
                new MyRouterObject(10008, USER_MANAGER_USER_INFO_GET));
        routerMapGet.put("USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME",
                new MyRouterObject(10009, USER_MANAGER_PERMISSION_GET_ALL_USERNAME_AND_ROLENAME));


        routerMapPost.put("LOG_HTTP_POST",
                new MyRouterObject(20000, LOG_HTTP_POST));
        routerMapPost.put("USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE",
                new MyRouterObject(20001, USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE));
        routerMapPost.put("USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER",
                new MyRouterObject(20002, USER_MANAGER_PERMISSION_MANAGER_ADD_ROLE_FOR_USER));
        routerMapPost.put("USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE",
                new MyRouterObject(20003, USER_MANAGER_PERMISSION_MANAGER_ADD_ROUTER_FOR_ROLE));


        routerMapDelete.put("USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID",
                new MyRouterObject(30000, USER_MANAGER_PERMISSION_MANAGER_DELETE_ROLE_BY_ID));

        routerMapPut.put("USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE",
                new MyRouterObject(40000, USER_MANAGER_PERMISSION_MANAGER_UPDATE_ROLE));
    }

    /**
     * 根据MethodName获取Router
     * @param methodName
     * @return
     */
    public static MyRouterObject getRouter(String method,String methodName){
        if(method == null || methodName == null){
            return null;
        }
        MyRouterObject routerObject ;
        switch (method){
            case REQUEST_METHOD_GET:
                routerObject  = MyRouter.routerMapGet.get(methodName); break;
            case REQUEST_METHOD_POST:
                routerObject  = MyRouter.routerMapPost.get(methodName); break;
            case REQUEST_METHOD_DELETE:
                routerObject  = MyRouter.routerMapDelete.get(methodName); break;
            case REQUEST_METHOD_PUT:
                routerObject  = MyRouter.routerMapPut.get(methodName); break;
            default:routerObject = null;break;
        }
        return routerObject;
    }

}
