package com.originaldreams.common.util;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 *  Response的工具类
 *  用于解析Response的返回结果
 */
public class ResponseUtils {

    /**
     * 判断Response返回码是否是成功
     * @param response
     * @return
     */
    public static boolean isSuccess(ResponseEntity<String> response){
        String result = response.getBody();
        JSONObject json = new JSONObject(result);
        int success = json.getInt(ConfigUtils.RESPONSE_RESULT_CODE_KEY);
        return success==ConfigUtils.RESPONSE_RESULT_CODE_SUCCESS_VALUE;
    }
}
