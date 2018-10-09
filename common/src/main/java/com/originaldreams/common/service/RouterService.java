package com.originaldreams.common.service;

import com.originaldreams.common.mapper.RouterMapper;
import com.originaldreams.common.router.MyRouterObject;
import com.originaldreams.common.router.MyServiceName;
import com.originaldreams.common.router.RouterAttribute;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangkaile
 * @date 2018-10-08 15:45:46
 */
@Service
public class RouterService {
    @Resource
    RouterMapper routerMapper;

    public static RouterService _instance = null;
    public static RouterService getInstance(){
        if(_instance == null){
            _instance = new RouterService();
        }
        return _instance;
    }


    /**
     * 获取一个方法的参数类型和参数列表 格式: 参数类型:参数名,参数类型:参数名......
     * @param method Method对象
     * @return
     */
    public  String getMethodParametersStr(Method method){
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

    public  List<MyRouterObject> initRouters(String serviceName,Class... controllers){

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
                    routerMapper.insert(routerObject);
                }
            }
        }

        return list;
    }

    public  String getRequestMappingValueStr(RequestMapping requestMapping){
        if(requestMapping != null){
            StringBuilder requestMappingValues = new StringBuilder();
            for(String string:requestMapping.value()){
                requestMappingValues.append(string);
            }
            return requestMappingValues.toString();
        }
        return null;
    }
    public  String getRequestMappingMethodStr(RequestMapping requestMapping){
        if(requestMapping != null){
            StringBuilder requestMappingMethods = new StringBuilder();
            for(RequestMethod requestMethod:requestMapping.method()){
                requestMappingMethods.append(requestMethod);
            }
            return requestMappingMethods.toString();
        }
        return null;
    }

}
