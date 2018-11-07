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
     * 获取邮箱验证码
     */
    public final static int GET_VERIFICATION_BY_EMAIL        = 30002;
    /**
     * 获取短信验证码
     */
    public final static int GET_VERIFICATION_BY_PHONE        = 30003;
    /**
     * 添加邮件发送记录
     */
    public final static int INSERT_EMAIL_SEND_LOG            = 30004;
    /**
     * 添加短信发送记录
     */
    public final static int INSERT_SMS_SEND_LOG = 30005;

}
