package com.originaldreams.serviceregistycenter.service;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.serviceregistycenter.mapper.RouterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public MyServiceResponse insert(MyRouterObject object){
       return new MyServiceResponse(routerMapper.insert(object));
    }

    public MyServiceResponse getAll(){
        return new MyServiceResponse(routerMapper.getAll());
    }

    public MyServiceResponse deleteByServiceName(String serviceName){
        return new MyServiceResponse(routerMapper.deleteByServiceName(serviceName));
    }

}
