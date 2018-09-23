package com.originaldreams.logcenter.controller;

import com.originaldreams.logcenter.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/http")
public class HttpController {
    @Autowired
    RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(HttpController.class);

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity get(Integer id,String name){
        if(id == null || name == null){
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("ASDFGHJKL");
            list.add("返回数据！！！");
            return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("id",id);
        result.put("name",name);
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    /**
     *
     * @param user 参数加上@RequestBody注解，就只能传对象，不能用json传递对象的部分属性
     *             如果不加@RequestBody，只能用json传递对象的属性，不能直接用restTemplate.postForEntity传递对象的方式
     * @return
     */
    @RequestMapping(value = "/postObject",method = RequestMethod.POST)
    public ResponseEntity postObject(@RequestBody User user){
        if(user != null){
            return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("请求参数异常！！！");
    }
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public ResponseEntity post(Integer id, String name){
        if(id == null || name == null){
            /*
                使用ResponseEntity.status(HttpStatus.BAD_REQUEST)这种形式返回的ResponseEntity，
                在用restTemplate请求时会抛HttpClientErrorException异常，需要请求方处理
             */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body("请求参数异常！！！");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("id",id);
        result.put("name",name);
        return  ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
    @RequestMapping(value = "/testGetToUserManagerCenter",method = RequestMethod.GET)
    public String testGetToUserManagerCenter(){
        ResponseEntity<String> responseEntity  = restTemplate.getForEntity("http://UserManagerCenter/user/getAll",String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        logger.info("test========" + result.toString());
        return result.toString();
    }


}
