package com.originaldreams.common.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.originaldreams.common.util.ConfigUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ResponseEntity解析类
 * @author yangkaile
 * @date 2018-10-12 09:55:02
 */
public class MyResponseReader {
    /**
     * 判断是否是成功应答
     * 要同时满足两个条件：1.http返回码200  2.业务返回码 0
     * @param response
     * @return
     */
    public static boolean isSuccess(ResponseEntity response){
        if(response.getStatusCode() != HttpStatus.OK ){
            return false;
        }
        JSONObject jsonObject = getJSONObject(response);
        if(jsonObject.get(ConfigUtils.SUCCESS_KEY) == null){
            return false;
        }
        return jsonObject.getInteger(ConfigUtils.SUCCESS_KEY) == ConfigUtils.RESPONSE_RESULT_CODE_SUCCESS_VALUE;
    }

    /**
     * 从ResponseEntity中拿list
     * @param response
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getList(ResponseEntity response, Class<T> clazz){
        JSONObject jsonObject = JSON.parseObject(getJSONString(response));
        return JSON.parseArray(jsonObject.getString("data"), clazz);
    }




    public static Integer getInteger(ResponseEntity responseEntity){
        return getJSONObject(responseEntity).getInteger(ConfigUtils.DATA_KEY);
    }
    public static <T> T getObject(ResponseEntity responseEntity,Class<T> clazz){
        return getJSONObject(responseEntity).getObject(ConfigUtils.DATA_KEY,clazz);
    }

    private static String getJSONString(ResponseEntity responseEntity){
        Object object = responseEntity.getBody();
        return JSON.toJSONString(object);
    }
    private static JSONObject getJSONObject(ResponseEntity responseEntity){
        return JSON.parseObject(getJSONString(responseEntity));
    }
}
