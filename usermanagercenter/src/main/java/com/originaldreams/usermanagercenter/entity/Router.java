package com.originaldreams.usermanagercenter.entity;


import com.originaldreams.common.router.MyRouterObject;

public class Router {
     private Integer id;
     private String serviceName;
     private String controllerName;
     private String methodName;
     private String routerUrl;
     private Long firstMask;
     private Long secondMask;
     private String requestMethod;

    public Router() {
    }

    public Router(Integer id, String serviceName, String controllerName, String methodName, String routerUrl, Long firstMask, Long secondMask) {
        this.id = id;
        this.serviceName = serviceName;
        this.controllerName = controllerName;
        this.methodName = methodName;
        this.routerUrl = routerUrl;
        this.firstMask = firstMask;
        this.secondMask = secondMask;
    }
    public static  Router parseRouter(MyRouterObject routerObject){
        return new Router(
                routerObject.getId(),
                routerObject.getServiceName(),
                routerObject.getControllerName(),
                routerObject.getMethodName(),
                routerObject.getRouterUrl(),
                routerObject.getFirstMask(),
                routerObject.getSecondMask()
        );
    }

    public Integer getId(){
           return this.id;
     }
     public void setId(Integer id){
           this.id = id;
     }
     public String getServiceName(){
           return this.serviceName;
     }
     public void setServiceName(String serviceName){
           this.serviceName = serviceName;
     }
     public String getControllerName(){
           return this.controllerName;
     }
     public void setControllerName(String controllerName){
           this.controllerName = controllerName;
     }
     public String getMethodName(){
           return this.methodName;
     }
     public void setMethodName(String methodName){
           this.methodName = methodName;
     }
     public String getRouterUrl(){
           return this.routerUrl;
     }
     public void setRouterUrl(String routerUrl){
           this.routerUrl = routerUrl;
     }
     public Long getFirstMask(){
           return this.firstMask;
     }
     public void setFirstMask(Long firstMask){
           this.firstMask = firstMask;
     }
     public Long getSecondMask(){
           return this.secondMask;
     }
     public void setSecondMask(Long secondMask){
           this.secondMask = secondMask;
     }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "Router{" +
            "  id:" + id + "  serviceName:" + serviceName + "  controllerName:" + controllerName + "  methodName:" + methodName + "  routerUrl:" + routerUrl + "  firstMask:" + firstMask + "  secondMask:" + secondMask + 
        "}";
    }
  }
