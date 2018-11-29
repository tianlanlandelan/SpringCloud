package com.originaldreams.usermanagercenter.entity;

import com.originaldreams.common.mybatis.FieldAttribute;
import com.originaldreams.common.mybatis.TableAttribute;

/**
 * Router实体类 使用@TableAttribute注解指定表名，使用@FieLdAttribute注解指定要全量查询的字段
 * @author yangkaile
 * @date 2018-09-28 15:08:00
 */
@TableAttribute("router")
public class Router {
    @FieldAttribute
    private Integer id;
    @FieldAttribute
    private String name;
    @FieldAttribute
    private String serviceName;
    @FieldAttribute
    private String controllerName;
    @FieldAttribute
    private String methodName;
    @FieldAttribute
    private String routerUrl;
    @FieldAttribute
    private String requestType;


    public Router() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRouterUrl() {
        return routerUrl;
    }

    public void setRouterUrl(String routerUrl) {
        this.routerUrl = routerUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", routerUrl='" + routerUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }
}
