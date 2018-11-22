package com.originaldreams.serviceregistycenter.service;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.response.ResultData;
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

    public ResultData insert(MyRouterObject object){
       return new ResultData(routerMapper.insert(object));
    }

    public ResultData getAll(){
        return new ResultData(routerMapper.getAll());
    }

    public ResultData deleteByServiceName(String serviceName){
        return new ResultData(routerMapper.deleteByServiceName(serviceName));
    }

}
