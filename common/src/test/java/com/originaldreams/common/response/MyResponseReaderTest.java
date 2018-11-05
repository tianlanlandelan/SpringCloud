package com.originaldreams.common.response;

import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.router.MyRouters;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class MyResponseReaderTest {
    @Test
    public void isSuccess(){
        MyServiceResponse myServiceResponse = new MyServiceResponse();
        ResponseEntity response = MyResponse.ok(myServiceResponse);
        System.out.println(MyResponseReader.isSuccess(response));
    }
    @Test
    public void getObject(){
        MyServiceResponse myServiceResponse = new MyServiceResponse();
        MyRouterObject object = new MyRouterObject();
        object.setRouterUrl("你大爷");
        object.setId(123);
        myServiceResponse.setData(object);
        ResponseEntity response = MyResponse.ok(myServiceResponse);
        System.out.println(MyResponseReader.getObject(response,MyRouterObject.class));
    }

    @Test
    public void getList(){
        MyServiceResponse myServiceResponse = new MyServiceResponse();
        List<MyRouterObject> list = new ArrayList<>();
        MyRouterObject object = new MyRouterObject();
        object.setRouterUrl("你大爷");
        object.setId(123);
        list.add(object);
        object.setName("接口1");
        list.add(object);
        object.setServiceName("UserManagerCenter");
        list.add(object);
        myServiceResponse.setData(list);
        ResponseEntity response = MyResponse.ok(myServiceResponse);
        System.out.println(MyResponseReader.getList(response,MyRouterObject.class));
    }

    @Test
    public void getInteger(){
        MyServiceResponse myServiceResponse = new MyServiceResponse();
        myServiceResponse.setData(123456);
        ResponseEntity response = MyResponse.ok(myServiceResponse);
        System.out.println(MyResponseReader.getInteger(response));
    }

    @Test
    public void getRouters(){
//        List<MyRouterObject> list =  MyRouters.getInstance().getRouters();
//        for(MyRouterObject object:list){
//            System.out.println(object);
//        }

    }

}
