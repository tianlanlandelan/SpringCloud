package com.originaldreams.common.router;

/**
 * @author yangkaile
 * @date 2018-09-18 13:41:49
 */
public class MyLogRouter {
    /**
     * 添加登录日志
     */
    public static final int INSERT_LOGON_LOG                 = 30001;
    /**
     * 校验邮件验证码
     */
    public final static int CHECK_EMAIL_CODE        = 30002;
    /**
     * 校验短信验证码
     */
    public final static int CHECK_SMS_CODE        = 30003;
    /**
     * 添加邮件发送记录
     */
    public final static int INSERT_EMAIL_SEND_LOG            = 30004;
    /**
     * 添加短信发送记录
     */
    public final static int INSERT_SMS_SEND_LOG = 30005;

}
