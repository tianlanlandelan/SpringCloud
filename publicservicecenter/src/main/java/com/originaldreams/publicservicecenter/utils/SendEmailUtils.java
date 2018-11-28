package com.originaldreams.publicservicecenter.utils;

import com.originaldreams.common.entity.EmailLog;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author yangkaile
 * @date 2018-09-11 14:34:03
 */
public class SendEmailUtils {
    private static Logger logger = LoggerFactory.getLogger(SendEmailUtils.class);

    private static final String HOST = "smtp.qq.com";
    private static final String USER = "guyexing@foxmail.com";
    private static final String PASSWORD = "pmjpoliwxjyadifd";

    private static  Session initSession(){
        Session session = null;
        try{
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.qq.com");
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");
            //使用JavaMail发送邮件的5个步骤
            //1、创建session
            session = Session.getInstance(prop);
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }
    public static void sendSimpleMail(String toEmailAddress,String title,String content)    throws Exception {
        Session session = initSession();
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(USER));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmailAddress));
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        Transport transport = session.getTransport();
        //使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器,用户名和密码都通过验证之后才能够正常发送邮件给收件人
        transport.connect(HOST,USER,PASSWORD);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static EmailLog sendVerificationCode(String email){
        String code = StringUtils.getAllCharString(MyConfigUtils.EMAIL_VERIFICATIONCODE_LENGTH);
        EmailLog entity = new EmailLog();
        entity.setEmail(email);
        entity.setType(MyConfigUtils.EMAIL_SEND_TYPE_REGISTER);
        entity.setTitle(MyConfigUtils.EMAIL_VERIFICATIONCODE_TITLE);
        entity.setContent(String.format(MyConfigUtils.EMAIL_VERIFICATIONCODE_BODY,code));
        entity.setCode(code);
        try {
            sendSimpleMail(entity.getEmail(),entity.getTitle(),entity.getContent());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("send sendVerificationCode error :" + e.getMessage());
            entity.setResult(e.getMessage());
            entity.setStatusCode(ConfigUtils.EMAIL_SEND_STATUSCODE_FAILED);
        }finally {
            return entity;
        }
    }
}
