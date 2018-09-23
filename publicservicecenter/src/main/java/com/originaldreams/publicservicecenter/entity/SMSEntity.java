package com.originaldreams.publicservicecenter.entity;

/**
 * 短信实体
 * @author yangkaile
 * @date 2018-08-28 14:08:54
 */
public class SMSEntity {
    /**
     * 接收方手机号
     */
    private String phone;
    /**
     * 消息类型
     */
    private Integer type;
    /**
     * 短信模板ID
     */
    private String templateId ;
    /**
     * 验证码
     * 模板里第一个参数
     * 123456
     */
    private String codeStr;
    /**
     * 有效时间
     * 模板里第二个参数
     * 单位：分钟
     */
    private String minuteStr;

    /**
     *  {data={templateSMS={dateCreated=20180827170721, smsMessageSid=a21809d2dbe84872878a3e9cd9a3da17}}, statusCode=000000}
     */ 
    private String result;
    /**
     *     000000
     */
    private String statusCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCodeStr() {
        return codeStr;
    }

    public void setCodeStr(String codeStr) {
        this.codeStr = codeStr;
    }

    public String getMinuteStr() {
        return minuteStr;
    }

    public void setMinuteStr(String minuteStr) {
        this.minuteStr = minuteStr;
    }

    @Override
    public String toString() {
        return "SMSEntity{" +
                "phone='" + phone + '\'' +
                ", type=" + type +
                ", templateId='" + templateId + '\'' +
                ", codeStr='" + codeStr + '\'' +
                ", minuteStr='" + minuteStr + '\'' +
                ", result='" + result + '\'' +
                ", statusCode='" + statusCode + '\'' +
                '}';
    }
}
