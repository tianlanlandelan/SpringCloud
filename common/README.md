## common组件说明文档
### 1、概述
common面对所有组件提供了统一的公用方法处理逻辑，如：加密解密、JSON处理、路由策略、Response格式等

### 2、结构说明
+ encryption    包含加密解密相关操作,如：Base64加解密、MD5加密
+ entity        包含公用的实体类，如：公用的路由对象、日志对象
+ exception     包含统一的错误处理
+ response      包含统一的应答格式
+ router        包含路由规则及路由相关操作
+ util          一些其他的处理工具

### 3、路由Router
router包下为路由规则及相关操作
#### 3.1、结构说明
+ MyServiceName.java    定义了各个服务的名称
+ RouterAttribute.java  定义了每个接口定义的格式
+ MyRouters.java        包含了路由注册、路由解析的所有方法
+ 其他                   定义了各个组件的下每个接口的路由Id

### 3.2、使用说明
1. 在要注册路由的每个接口上引入@RouterAttribute注解，该注解包含两个参数：id:接口Id,description:接口说明
```java
    @RouterAttribute(id = MyUserManagerRouter.LOGON, description = "我是登录接口")
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public ResponseEntity logon(String userName,String password){
        ... ...
        }
    }
```
2. 在要注册路由的组件启动时，调用MyRouters.getInstance().initRouters(String serviceName,Class... controllers)执行路由初始化操作
```java
    MyRouters.getInstance().initRouters(MyServiceName.USER_MANAGER_CENTER,
                LogonController.class,
                UserInfoController.class);
```
经过以上操作，组件的所有路由信息（接口）全部会注册到服务注册中心。