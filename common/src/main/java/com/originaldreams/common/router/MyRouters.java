package com.originaldreams.common.router;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.response.MyResponseReader;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.common.util.StringUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangkaile
 * @date 2018-10-09 09:37:33
 */
public class MyRouters {
    public static Map<Integer,MyRouterObject> routerMap = new HashMap();
    public static void initRouterMap(List<MyRouterObject> list){
        routerMap.clear();
        for (MyRouterObject myRouterObject:list){
            routerMap.put(myRouterObject.getId(),myRouterObject);
        }
    }

    /**
     * 获取一个方法的参数类型和参数列表 格式: 参数类型:参数名,参数类型:参数名......
     * @param method Method对象
     * @return
     */
    public static  String getMethodParametersStr(Method method){
        StringBuilder builder = new StringBuilder();
        LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer
                = new LocalVariableTableParameterNameDiscoverer();
        //取参数名列表
        String[] params = parameterNameDiscoverer.getParameterNames(method);
        //取参数类型列表
        Class[] paramTypes = method.getParameterTypes();

        for (int i = 0; i < params.length; i++) {
            builder.append(paramTypes[i].getSimpleName())
                    .append(":")
                    .append(params[i]);
            if(i < params.length - 1){
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public static  List<MyRouterObject> initRouters(String serviceName,Class... controllers){

        Class[] controllerArray = controllers;
        List<MyRouterObject> list = new ArrayList<>();
        for(Class t:controllerArray){
            //获取Class上的RequestMapping信息（路由前缀）

            String urlPrefix = getRequestMappingValueStr((RequestMapping)t.getAnnotation(RequestMapping.class));
            //获取Class的方法列表
            Method[] methods = t.getDeclaredMethods();
            for(Method method:methods){
                //获取@RouterAttribute注解信息
                RouterAttribute routerAttribute = method.getAnnotation(RouterAttribute.class);
                if(routerAttribute != null){
                    MyRouterObject routerObject = new MyRouterObject();
                    routerObject.setId(routerAttribute.id());
                    routerObject.setName(method.getName() + routerObject.getId());
                    routerObject.setDescription(routerAttribute.description());
                    routerObject.setServiceName(MyServiceName.USER_MANAGER_CENTER);
                    routerObject.setControllerName(t.getSimpleName());
                    routerObject.setMethodName(method.getName());
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if(StringUtils.isEmpty(urlPrefix)){
                        routerObject.setRouterUrl(ConfigUtils.HTTP_UTL_PREFIX + serviceName + getRequestMappingValueStr(requestMapping));
                    }else {
                        routerObject.setRouterUrl(ConfigUtils.HTTP_UTL_PREFIX + serviceName + urlPrefix + getRequestMappingValueStr(requestMapping));
                    }
                    routerObject.setRequestType(getRequestMappingMethodStr(requestMapping));
                    routerObject.setParameters(getMethodParametersStr(method));

                    list.add(routerObject);
                    //TODO 注册路由
                    registerRouters(routerObject);
                }
            }
        }
        return list;
    }

    public static void registerRouters(MyRouterObject object){
        RestTemplate restTemplate = getRestTemplate();
        restTemplate.postForObject(ConfigUtils.ROUTER_REGISTER_URL,object,String.class);
    }


    public static List<MyRouterObject> getRouters(){
        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(ConfigUtils.GET_ROUTERS_URL,String.class);
        System.out.println(MyResponseReader.getJSONString(responseEntity));
        List<MyRouterObject> list = MyResponseReader.getList(responseEntity,MyRouterObject.class);
        MyRouters.initRouterMap(list);
        return list;
    }

    public static  String getRouterUrl(Integer id){
        if(routerMap == null || routerMap.isEmpty()){
            getRouters();
        }
       return routerMap.get(id).getRouterUrl();
    }

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }


    public  static String getRequestMappingValueStr(RequestMapping requestMapping){
        if(requestMapping != null){
            StringBuilder requestMappingValues = new StringBuilder();
            for(String string:requestMapping.value()){
                requestMappingValues.append(string);
            }
            return requestMappingValues.toString();
        }
        return null;
    }
    public static  String getRequestMappingMethodStr(RequestMapping requestMapping){
        if(requestMapping != null){
            StringBuilder requestMappingMethods = new StringBuilder();
            for(RequestMethod requestMethod:requestMapping.method()){
                requestMappingMethods.append(requestMethod);
            }
            return requestMappingMethods.toString();
        }
        return null;
    }

    public static void  main(String[] args){
        System.out.println(getRouterUrl(MyUserManagerRouter.LOGON));
    }

}

