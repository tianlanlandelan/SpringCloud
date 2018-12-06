package com.originaldreams.usermanagercenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.originaldreams.common.entity.Router;
import com.originaldreams.common.util.ConfigUtils;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;

public class MyRoutersTest {
    @Test
    public  void readRouters() {

//        List<Router> list = MyRouters.getInstance().initRouters(MyServiceName.USER_MANAGER_CENTER,
//                LogonController.class,
//                PermissionController.class,
//                PermissionManagerController.class,
//                UserInfoController.class);
//        RestTemplate restTemplate = new RestTemplate();

    }

    @Test
    public void getRouters(){
//        String GET_ROUTERS_URL = "http://127.0.0.1:8801/getRouters";
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(GET_ROUTERS_URL,String.class);
//        System.out.println("registerRouters:" + responseEntity.getBody());
//        System.out.println("===================");
//        if(isSuccess(responseEntity)){
//            List<Router> list =  getEntity(responseEntity,Router.class);
//
//            for(Router object : list){
//                System.out.println(object);
//            }
//        }
    }
    private boolean isSuccess(ResponseEntity<String> response){
        if(response.getStatusCode() != HttpStatus.OK ){
            return false;
        }
        String result = response.getBody();
        JSONObject jsonObject = JSON.parseObject(result);
        if(jsonObject.get("success") == null){
            return false;
        }
        return jsonObject.getInteger("success") == ConfigUtils.RESPONSE_RESULT_CODE_SUCCESS_VALUE;
    }
    private <T> List<T> getEntity(ResponseEntity<String> response,Class T){
        String result = response.getBody();
        JSONObject jsonObject = JSON.parseObject(result);
//        System.out.println(list);
        return JSON.parseArray(jsonObject.getString("data"), T);
    }



}
