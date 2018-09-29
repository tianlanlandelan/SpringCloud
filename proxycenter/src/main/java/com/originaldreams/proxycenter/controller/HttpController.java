package com.originaldreams.proxycenter.controller;

import com.originaldreams.common.encryption.MyBase64Utils;
import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyServiceResponse;
import com.originaldreams.common.router.*;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.common.util.StringUtils;
import com.originaldreams.common.util.ValidUserName;
import com.originaldreams.proxycenter.cache.CacheUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨凯乐
 * @date   2018-07-28 19:00:16
 */
@RestController
@RequestMapping("/api")
public class HttpController {
    private Logger logger = LoggerFactory.getLogger(HttpController.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;

    private final static String USER_ID = "userId";

    /**
     * 客户端传参时，多个参数之间的分隔符
     */
    private final static String SPLIT_PARAMETERS = ";";

    /**
     * 客户端传参时，Key_Value间的分隔符
     */
    private final static String SPLIT_KEY_VALUE = ":";

    private static final String NULL_STRING = "null";

    private static final String API_PREFIX = "/api";

    /**
     * 统一的登录接口
     *  TODO 提供统一的用户名、手机号、邮箱识别方法 涉及到用户名规则的制定
     * @param userName  用户名、手机号、邮箱
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public ResponseEntity logon(String userName,String password){
        try {
            logger.info("logon  userName:" + userName);
            if(userName == null || password == null){
                return MyResponse.badRequest();
            }
            Map<String, String> map = new HashMap<>();
            map.put("password",password);
            ResponseEntity<String> responseEntity = null;
            //手机号
            if(ValidUserName.isValidPhoneNumber(userName)){
                map.put("phone",userName);
                responseEntity = restTemplate.postForEntity(
                        MyUserManagerRouter.getInstance().USER_MANAGER_LOGON.getRouterUrl()
                         + "?email={phone}&password={password}",null,String.class,map);
            }
            //邮箱

            else if(ValidUserName.isValidEmailAddress(userName)){
                map.put("email", userName);
                responseEntity = restTemplate.postForEntity(
                        MyUserManagerRouter.getInstance().USER_MANAGER_LOGON.getRouterUrl()
                                + "?phone={email}&password={password}",null,String.class,map);
            }
            //用户名
            else if(ValidUserName.isValidUserName(userName)){
                map.put("userName", userName);
                responseEntity = restTemplate.postForEntity(
                        MyUserManagerRouter.getInstance().USER_MANAGER_LOGON.getRouterUrl()
                                + "?userName={userName}&password={password}",null,String.class,map);
            }
            if(responseEntity == null){
                return MyResponse.badRequest();
            }
            //登录成功，缓存
            setCacheForLogon(responseEntity);
            return  responseEntity;
        }catch (HttpClientErrorException e){
            e.printStackTrace();
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }
    }

    /**
     * 注册
     * @param userName 手机号或邮箱
     * @param password  密码
     * @param verificationCode 验证码
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(String userName,String password,String verificationCode){
        try {
            logger.info("register  :" );
            if(StringUtils.isEmpty(userName,password,verificationCode)){
                return MyResponse.badRequest();
            }
            Map<String, String> map = new HashMap<>();
            map.put("userName",userName);
            map.put("password",password);
            map.put("verificationCode",verificationCode);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                    MyUserManagerRouter.getInstance().USER_MANAGER_REGISTER.getRouterUrl() +
                    "?userName={userName}&password={password}&verificationCode={verificationCode}",
                    null,String.class,map);
            return  responseEntity;
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }
    }

    @RequestMapping(value = "/sendVerificationCode",method = RequestMethod.GET)
    public ResponseEntity sendVerificationCode(String phone){
        try {
            logger.info("register  :" );
            if(phone == null || phone.isEmpty()){
                return MyResponse.badRequest();
            }
            Map<String, String> map = new HashMap<>();
            map.put("phone",phone);

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyPublicServiceRouter.getInstance().PUBLIC_SERVICE_SMS_SEND_VERIFICATIONCODE.getRouterUrl()
                            + "?phone={phone}",String.class,map);
            return  responseEntity;
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }
    }
    /**
     * 针对一般用户所有get请求的转发
     * 请求格式如：localhost:8805?methodName=USER_MANAGER_PERMISSION_GET_ROLES_BY_ROUTER_ID&parameters=routerId:MTAwMDE=
     * 特点：当查询条件为用户id时，不用上传用户id
     * 1.鉴权
     * 2.转发
     * 3.针对错误返回码（401、403等）转处理为不同的应答
     * @param methodName    方法名
     * @param parameters    参数 格式：key1:base64(value1);key2:base64(value2) 如：routerId:MTAwMDE=
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(String methodName,String parameters){
        if(methodName == null){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_GET,methodName);
        if(routerUrl == null){
            return MyResponse.forbidden();
        }
        ResponseEntity<String> responseEntity;
        try{
            /**
             * TODO 这里出现一个问题，用户是否可以查看别人的信息？用户查看别人的信息时，需不需要隐藏一些敏感信息
             * 允许管理员在接口中传入userId参数（允许其操作其他User的数据）
             * 不允许普通用户传递（不允许其操作其他User的数据）
             */
            if(isManager()){
                //Manager的空参数请求，说明就是空参数
                if(parameters == null){
                    responseEntity = restTemplate.getForEntity(routerUrl,String.class);
                }else{
                    //url后拼接的请求参数格式
                    String urlParameters = getUrlParameters(parameters);
                    routerUrl += urlParameters;
                    //请求参数
                    Map<String,Object> map = parseMap(parameters);
                    logger.info("get  methodName:" + methodName + ",url:" + routerUrl);
                    responseEntity = restTemplate.getForEntity(routerUrl,String.class,map);
                }
            }else{
                //User的空参数请求自动拼接userId
                if(parameters == null){
                    responseEntity = restTemplate.getForEntity(routerUrl + "?" + USER_ID+ "=" + getUserId(),String.class);
                }else{
                    //url后拼接的请求参数格式,原则上不允许上传userId，当请求参数中有userId时，会被改写为自己的userId
                    String urlParameters = getUrlParametersWithUserId(parameters);
                    routerUrl += urlParameters;
                    //请求参数
                    Map<String,Object> map = parseMapWithUserId(parameters);
                    logger.info("get  methodName:" + methodName + ",url:" + routerUrl);
                    responseEntity = restTemplate.getForEntity(routerUrl,String.class,map);
                }

            }

        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return responseEntity;
    }
    /**
     * POST请求不允许空参数
     * @param methodName 请求的方法名
     * @param parameters 请求参数
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity post(String methodName,String parameters){
        if(methodName == null || parameters == null){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_POST,methodName);
        if(routerUrl == null){
            return MyResponse.forbidden();
        }
        ResponseEntity<String> responseEntity;
        try{
            Map<String,Object> map ;
            if(isManager()){
                routerUrl = routerUrl + getUrlParameters(parameters);
                map = parseMap(parameters);
            }else{
                routerUrl = routerUrl + getUrlParametersWithUserId(parameters);
                map = parseMapWithUserId(parameters);
            }
            logger.info("post  methodName:" + methodName + ",url:" + routerUrl);
            responseEntity = restTemplate.postForEntity(routerUrl,null,String.class,map);
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return responseEntity;
    }

    /**
     * DELETE请求不允许参数为空
     * @param methodName
     * @param parameters
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete(String methodName,String parameters){
        if(methodName == null || parameters == null){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_DELETE,methodName);
        if(routerUrl == null){
            return MyResponse.forbidden();
        }
        try{
            Map<String,Object> map ;
            if(isManager()){
                routerUrl = routerUrl + getUrlParameters(parameters);
                map = parseMap(parameters);
            }else{
                routerUrl = routerUrl + getUrlParametersWithUserId(parameters);
                map = parseMapWithUserId(parameters);
            }
            logger.info("delete methodName:" + methodName + ",url:" + routerUrl);
            restTemplate.delete(routerUrl,map);
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(new MyServiceResponse("删除成功"));
    }

    /**
     * PUT不允许空参数
     * @param methodName
     * @param parameters
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(String methodName,String parameters){
        if(methodName == null || parameters == null){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_PUT,methodName);
        if(routerUrl == null){
            return MyResponse.forbidden();
        }
        try{
            Map<String,Object> map ;
            if(isManager()){
                routerUrl = routerUrl + getUrlParameters(parameters);
                map = parseMap(parameters);
            }else{
                routerUrl = routerUrl + getUrlParametersWithUserId(parameters);
                map = parseMapWithUserId(parameters);
            }
            logger.info("put methodName:" + methodName + ",url:" + routerUrl);
            restTemplate.put(routerUrl,null,map);
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }catch (Exception e){
            return MyResponse.badRequest();
        }
        return MyResponse.ok(new MyServiceResponse("修改成功"));
    }

    /**
     * 鉴权
     * @param methodName 客户端调用的方法名
     * @return
     */
    private String authenticateAndReturnRouterUrl(String method,String methodName){
        Integer userId = getUserId();
        if(userId == null){
            return null;
        }
        List<Integer> routerIdList = CacheUtils.userRouterMap.get(getUserId());

        MyRouterObject routerObject = MyRouters.getRouterObject(methodName);

        if(routerObject == null || routerIdList == null || routerIdList.size() < 1){
            return null;
        }
        Integer routerId = routerObject.getId();
        String routerUrl = routerIdList.contains(routerId)?routerObject.getRouterUrl():null;
        return routerUrl;

    }



    /**
     * 根据参数生成Map
     * @param parameters    加密过的参数
     * @return
     * @throws Exception
     */
    private Map<String,Object> parseMap(String parameters) throws Exception{
        if(parameters == null){
            return null;
        }
        Map<String ,Object> map = new HashMap<>();
        for(String kValue : parameters.split(SPLIT_PARAMETERS)){
            String[] array = kValue.split(SPLIT_KEY_VALUE);
            String key = array[0];
            String value = MyBase64Utils.decode(array[1]);
            map.put(key,value);
        }
        return  map;
    }

    /**
     * 根据参数生成Map （含userId）
     * @param parameters    加密过的参数
     * @return
     * @throws Exception
     */
    private Map<String,Object> parseMapWithUserId(String parameters) throws Exception {
        Map<String ,Object> map = parseMap(parameters);
        map.put(USER_ID,getUserId());
        return map;
    }

    /**
     * 获取Url参数
     * @param parameters 加密过的参数
     * @return
     * @throws Exception
     */
    private String getUrlParameters(String parameters) throws Exception{
        if(parameters == null){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("?");
        for(String kValue : parameters.split(SPLIT_PARAMETERS)){
            String[] array = kValue.split(SPLIT_KEY_VALUE);
            String key = array[0];
           builder.append(key).append("={").append(key).append("}&");
        }
        return builder.toString();
    }

    /**
     * 获取Url参数（含UserId）
     * @param parameters    加密过的参数
     * @return
     * @throws Exception
     */
    private String getUrlParametersWithUserId(String parameters) throws  Exception{
        return getUrlParameters(parameters) + USER_ID +"={" + USER_ID + "}";
    }

    /**
     * 根据组件返回的错误码重组应答报文
     * @param exception
     * @return
     */
    private ResponseEntity getResponseFromException(HttpClientErrorException exception){
        ResponseEntity response;
        switch (exception.getStatusCode()){
            case FORBIDDEN:  response = MyResponse.forbidden(); break;
            case BAD_REQUEST: response = MyResponse.badRequest();break;
            case UNAUTHORIZED: response = MyResponse.unauthorized();break;
            default:{
                MyServiceResponse myServiceResponse = new MyServiceResponse(MyServiceResponse.SUCCESS_CODE_FAILED,"未知错误");
                response = ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(myServiceResponse);
            }
        }
        return  response;
    }

    /**
     * 设置登录时的缓存
     * 包含请求session和用户权限缓存
     * @param response
     */
    private void setCacheForLogon(ResponseEntity<String> response){
        String result = response.getBody();

        try{

            JSONObject json = new JSONObject(result);
            int success = json.getInt("success");
            //登录不成功，不记录session
            if(success != 0 ){
                return;
            }
            Object data = json.get("data");
            if (NULL_STRING.equals(data.toString())) {
                return;
            }
            int userId = json.getInt("data");
            logger.info("logonWithUserName userId:" + userId);
            //将userId放入Session
            request.getSession().setAttribute("userId",userId);

            //查询用户的权限Id
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyUserManagerRouter.getInstance().USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID.getRouterUrl() + "?" + USER_ID +"=" + userId,String.class);

            logger.info("USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID response:" + responseEntity.getBody());
            //将查询到的Id列表转化为List，放入缓存
            json = new JSONObject(responseEntity.getBody());
            json.getJSONArray("data");
            List<Object> list = json.getJSONArray("data").toList();
            List<Integer> routerIds = new ArrayList<>();
            for(Object object:list){
                routerIds.add((int)object);
            }
            //routerIds放入缓存
            CacheUtils.userRouterMap.put(userId,routerIds);
            //用户权限放入缓存
            responseEntity = restTemplate.getForEntity(
                    MyUserManagerRouter.getInstance().USER_MANAGER_PERMISSION_GET_ROLE_BY_USER_ID.getRouterUrl() + "?" + USER_ID +"=" + userId,String.class);

            json = new JSONObject(responseEntity.getBody());
            String roleName = json.getJSONObject("data").getString("name");
            //角色名放入缓存
            CacheUtils.userRoleMap.put(userId,roleName);
            logger.info("logonWithUserName roleName :" + roleName + ", routerIds:" + routerIds);
        }catch (JSONException e){
            e.printStackTrace();
            logger.error("setCacheForLogon {}", e.getMessage());

        }

    }

    private Integer getUserId(){
        Object object = request.getSession().getAttribute("userId");
        if(object == null){
            return null;
        }else{
            try {
                return (int)object;
            }catch (Exception e){
                logger.error("session获取失败:" + object);
                return null;
            }
        }
    }

    /**
     * TODO 角色准备初始化在common里面，每次UserManagerCenter启动时刷到DB中
     * @return
     */
    private boolean isManager(){
        Integer userId = getUserId();
        String roleName = CacheUtils.userRoleMap.get(userId);
        if(roleName == null || roleName.equals("User")){
            return false;
        }else if(roleName.equals("Manager")){
            return true;
        }
        return false;
    }

}
