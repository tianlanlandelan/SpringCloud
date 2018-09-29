package com.originaldreams.common.router;

import com.originaldreams.common.util.ConfigUtils;

/**
 * @author yangkaile
 * @date 2018-09-18 14:51:05
 */
public class MyPublicServiceRouter extends MyRouter {

    @Override
    public int getServiceRouterId(){
        return ConfigUtils.ROUTER_ID_PUBLIC_SERVICE;
    }

    @Override
    public String getServiceName(){
        return MyServiceName.PUBLIC_SERVICE_CENTER;
    }

    @Override
    public void init(){
        addRouter(PUBLIC_SERVICE_SMS_SEND_VERIFICATIONCODE);
    }

    private static MyPublicServiceRouter instance;
    public static MyPublicServiceRouter getInstance(){
        if(instance == null){
            instance = new MyPublicServiceRouter();
        }
        return instance;
    }
    /**
     * 发送短信验证码
     * GER
     * phone:String notNull
     */
    public final  MyRouterObject PUBLIC_SERVICE_SMS_SEND_VERIFICATIONCODE =
            new MyRouterObject(getRouterId(1),
                    "PUBLIC_SERVICE_SMS_SEND_VERIFICATIONCODE",
                    getUrl("/SMS/sendVerificationCode"),
                    ConfigUtils.REQUEST_METHOD_GET);



}
