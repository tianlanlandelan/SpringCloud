package com.originaldreams.usermanagercenter.entity;

/**
 * @author yangkaile
 * @date 2018-09-28 15:08:00
 */
public class Router {
    private Integer id;
    private String name;
    private String serviceName;
    private String controllerName;
    private String methodName;
    private String routerUrl;
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
