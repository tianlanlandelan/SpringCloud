package com.originaldreams.publicservicecenter.entity;

import com.originaldreams.publicservicecenter.utils.ConfigUtils;

/**
 * @author yangkaile
 * @date 2018-09-11 14:39:17
 */
public class EmailEntity {
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮件类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 验证码
     * 包含验证码的邮件，需要将验证码单独填写，方便查询
     */
    private String code;
    /**
     * 执行结果
     * 如果有错误，这里记录错误信息
     */
    private String result;
    /**
     * 状态码
     * 0 成功
     * 1 失败
     */
    private int statusCode = ConfigUtils.EMAIL_SEND_STATUSCODE_SUCCESS;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "EmailEntity{" +
                "email='" + email + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", result='" + result + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
