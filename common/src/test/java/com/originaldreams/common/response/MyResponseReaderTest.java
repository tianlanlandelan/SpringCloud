package com.originaldreams.common.response;

import com.originaldreams.common.entity.Router;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class MyResponseReaderTest {
    @Test
    public void isSuccess(){
        ResultData resultData = new ResultData();
        ResponseEntity response = MyResponse.ok(resultData);
        System.out.println(MyResponseReader.isSuccess(response));
    }
    @Test
    public void getObject(){
        ResultData resultData = new ResultData();
        Router object = new Router();
        object.setRouterUrl("你大爷");
        object.setId(123);
        resultData.setData(object);
        ResponseEntity response = MyResponse.ok(resultData);
        System.out.println(MyResponseReader.getObject(response,Router.class));
    }

    @Test
    public void getList(){
        ResultData resultData = new ResultData();
        List<Router> list = new ArrayList<>();
        Router object = new Router();
        object.setRouterUrl("你大爷");
        object.setId(123);
        list.add(object);
        object.setName("接口1");
        list.add(object);
        object.setServiceName("UserManagerCenter");
        list.add(object);
        resultData.setData(list);
        ResponseEntity response = MyResponse.ok(resultData);
        System.out.println(MyResponseReader.getList(response,Router.class));
    }

    @Test
    public void getInteger(){
        ResultData resultData = new ResultData();
        resultData.setData(123456);
        ResponseEntity response = MyResponse.ok(resultData);
        System.out.println(MyResponseReader.getInteger(response));
    }

    @Test
    public void getRouters(){
//        List<Router> list =  MyRouters.getInstance().getRouters();
//        for(Router object:list){
//            System.out.println(object);
//        }

    }

}
