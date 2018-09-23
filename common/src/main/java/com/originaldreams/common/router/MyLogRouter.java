package com.originaldreams.common.router;

import com.originaldreams.common.util.ConfigUtils;

/**
 * @author yangkaile
 * @date 2018-09-18 13:41:49
 */
public class MyLogRouter extends MyNewRouter {
    @Override
    public int getServiceRouterId(){
        return ConfigUtils.ROUTER_ID_LOG;
    }

    @Override
    public String getServiceName(){
        return MyServiceName.LOG_CENTER;
    }

    private static MyLogRouter instance;
    public static MyLogRouter getInstance(){
        if(instance == null){
            instance = new MyLogRouter();
        }
        return instance;
    }

    @Override
    public  void init(){
        addRouter(LOG_HTTP_GET);
        addRouter(LOG_HTTP_POST);
        addRouter(LOG_LOGON_LOG_INSERT);
        addRouter(LOG_SMS_LOG_INSERT);
        addRouter(LOG_SMSLOG_CHECK_AND_UPDATE_STATE);
    }


    /**
     * LogCenter提供的测试http接口
     * GET
     * id: Integer，null
     * name: String，null
     */
    public final  MyRouterObject LOG_HTTP_GET =
            new MyRouterObject(getRouterId(1),
                    "LOG_HTTP_GET",
                    getUrl("/http/get"),
                    ConfigUtils.REQUEST_METHOD_GET);


    /**
     * LogCenter提供的测试http接口
     * POST
     * id: Integer，notNull
     * name: String，notNull
     */
    public final  MyRouterObject LOG_HTTP_POST =
            new MyRouterObject(getRouterId(2),
                    "LOG_HTTP_POST",
                    getUrl("/http/post"),
                    ConfigUtils.REQUEST_METHOD_POST);


    /**
     * 验证短信验证码是否正确
     * GET
     * phone 手机号 notNull
     * codeStr 验证码 notNull
     */
    public final  MyRouterObject LOG_SMSLOG_CHECK_AND_UPDATE_STATE =
            new MyRouterObject(getRouterId(3),
                    "LOG_SMSLOG_CHECK_AND_UPDATE_STATE",
                    getUrl("/smsLog/checkAndUpdateState"),
                    ConfigUtils.REQUEST_METHOD_GET);

    /**
     * 添加用户登录日志
     * POST
     * userId:Integer notNull
     * type:Integer notNull
     * way:Integer notNull
     * ip:String null
     * deviceId:String null
     */
    public final  MyRouterObject LOG_LOGON_LOG_INSERT =
            new MyRouterObject(getRouterId(4),
                    "LOG_LOGON_LOG_INSERT",
                    getUrl("/logonLog/insert"),ConfigUtils.REQUEST_METHOD_GET);

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
    public final  MyRouterObject LOG_SMS_LOG_INSERT =
            new MyRouterObject(getRouterId(5),
                    "LOG_SMS_LOG_INSERT",
                    getUrl( "/smsLog/insert"),ConfigUtils.REQUEST_METHOD_GET);

}
