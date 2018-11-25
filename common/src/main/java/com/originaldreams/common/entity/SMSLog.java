package com.originaldreams.common.entity;

import java.util.Date;

public class SMSLog {
    /**
    * id
    */
     private Integer id;
    /**
    * 用户ID
    */
     private String phone;
    /**
    * 类型
    */
     private Integer type;
    /**
    * 短信模板ID
    */
     private String templateId;
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
     *  返回结果
     *  {data={templateSMS={dateCreated=20180827170721, smsMessageSid=a21809d2dbe84872878a3e9cd9a3da17}}, statusCode=000000}
     */
    private String result;
    /**
    * 返回码
    */
     private String statusCode;
    /**
     * 状态 0 正常  1 已使用
     */
    private int state = 0;
    /**
    * 时间
    */
     private Date createTime = new Date();
     public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getPhone(){
           return this.phone;
     }
     public void setPhone(String phone){
           this.phone = phone;
     }
     public Integer getType(){
           return this.type;
     }
     public void setType(Integer type){
           this.type = type;
     }
     public String getTemplateId(){
           return this.templateId;
     }
     public void setTemplateId(String templateId){
           this.templateId = templateId;
     }
     public String getCodeStr(){
           return this.codeStr;
     }
     public void setCodeStr(String codeStr){
           this.codeStr = codeStr;
     }
     public String getMinuteStr(){
           return this.minuteStr;
     }
     public void setMinuteStr(String minuteStr){
           this.minuteStr = minuteStr;
     }
     public String getResult(){
           return this.result;
     }
     public void setResult(String result){
           this.result = result;
     }
     public String getStatusCode(){
           return this.statusCode;
     }
     public void setStatusCode(String stateCode){
           this.statusCode = stateCode;
     }
     public Date getCreateTime(){
           return this.createTime;
     }
     public void setCreateTime(Date createTime){
           this.createTime = createTime;
     }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SMSLog{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                ", templateId='" + templateId + '\'' +
                ", codeStr='" + codeStr + '\'' +
                ", minuteStr='" + minuteStr + '\'' +
                ", result='" + result + '\'' +
                ", statusCode='" + statusCode + '\'' +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }
}
