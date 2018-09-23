package com.originaldreams.common.router;

/**
 * @author 杨凯乐
 * @date 2018-08-02 09:33:01
 */
public class MyRouterObject {
    /**
     * 接口ID
     */
    private Integer id;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 组件名称
     */
    private String serviceName;
    /**
     * Controller名称
     */
    private String controllerName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 路由
     */
    private String routerUrl;
    /**
     * 请求类型
     */
    private String requestType;



    /**
     * @param id
     * @param routerUrl 格式：http://UserManagerCenter/permission/getAllRoles"
     */
    public MyRouterObject(Integer id, String routerUrl){
        this.id = id;
        this.routerUrl = routerUrl;
        /*
        这里可能产生数组越界抛异常，当抛异常时，说明routerUrl格式不正确，需要修改
         */
        try {
            String[] array = routerUrl.split("/");
            this.serviceName = array[2];
            this.controllerName = array[3];
            if(array.length >= 5){
                this.methodName = array[4];
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("routerUrl格式不正确");
        }
    }
    public MyRouterObject(Integer id, String name,String routerUrl,String requestType){
        this.id = id;
        this.name = name;
        this.routerUrl = routerUrl;
        this.requestType = requestType;
        /*
        这里可能产生数组越界抛异常，当抛异常时，说明routerUrl格式不正确，需要修改
         */
        try {
            String[] array = routerUrl.split("/");
            this.serviceName = array[2];
            this.controllerName = array[3];
            if(array.length >= 5){
                this.methodName = array[4];
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("routerUrl格式不正确");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "MyRouterObject{" +
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
