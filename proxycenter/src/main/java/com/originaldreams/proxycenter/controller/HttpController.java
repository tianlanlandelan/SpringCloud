package com.originaldreams.proxycenter.controller;

import com.originaldreams.common.encryption.MyBase64Utils;
import com.originaldreams.common.entity.MyRouterObject;
import com.originaldreams.common.response.MyResponse;
import com.originaldreams.common.response.MyResponseReader;
import com.originaldreams.common.response.ResultData;
import com.originaldreams.common.router.*;
import com.originaldreams.common.util.ConfigUtils;
import com.originaldreams.common.util.JsonUtils;
import com.originaldreams.common.util.StringUtils;
import com.originaldreams.proxycenter.cache.CacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨凯乐
 * @date   2018-07-28 19:00:16
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*",allowedHeaders="*", maxAge = 3600)
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

    /**
     * 统一的登录接口
     * @param json
     * @param userName  用户名、手机号或邮箱
     * @param password  密码
     * @return
     */
    @RequestMapping(value = "/logon",method = RequestMethod.POST)
    public ResponseEntity logon(@RequestBody String json,String userName,String password){
        if(StringUtils.isEmpty(userName,password)){
            userName = JsonUtils.getString(json,"userName");
            password = JsonUtils.getString(json,"password");
        }
        logger.info("userName:" + userName + "，password:" + password);
        try {
            logger.info("logon  userName:" + userName);
            if(StringUtils.isEmpty(userName,password)){
                return MyResponse.badRequest();
            }
            Map<String, String> map = new HashMap<>(16);
            map.put("userName", userName);
            map.put("password",password);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                        MyRouters.getRouterUrl(MyUserManagerRouter.LOGON)
                                + "?userName={userName}&password={password}",null,String.class,map);
            if(!MyResponseReader.isSuccess(responseEntity)){
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
     * 注册接口
     * @param json
     * @param userName  用户名
     * @param password  密码
     * @param verificationCode  验证码
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody String json,String userName,String password,String verificationCode){
        if(StringUtils.isEmpty(userName,password,verificationCode)){
            userName = JsonUtils.getString(json,"userName");
            password = JsonUtils.getString(json,"password");
            verificationCode = JsonUtils.getString(json,"verificationCode");
        }
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
                    MyRouters.getRouterUrl(MyUserManagerRouter.REGISTER)
                            + "?userName={userName}&password={password}&verificationCode={verificationCode}",
                    null,String.class,map);
            return  responseEntity;
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }
    }

    /**
     * 发送短信验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/sendSMSCode",method = RequestMethod.GET)
    public ResponseEntity sendSMSCode(String phone){
        try {
            if(StringUtils.isEmpty(phone)){
                return MyResponse.badRequest();
            }
            logger.info("sendSMSCode  :" + phone );
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyRouters.getRouterUrl(MyPublicServiceRouter.SEND_VERIFICATION_CODE_SMS) + "?phone=" + phone, String.class);
            return  responseEntity;
        }catch (HttpClientErrorException e){
            logger.warn("HttpClientErrorException:" + e.getStatusCode());
            return getResponseFromException(e);
        }
    }

    /**
     * 发送邮件验证码
     * @param email
     * @return
     */
    @RequestMapping(value = "/sendEmailCode",method = RequestMethod.GET)
    public ResponseEntity sendEmailCode(String email){
        try {
            if(StringUtils.isEmpty(email)){
                return MyResponse.badRequest();
            }
            logger.info("sendEmailCode  :" + email );
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyRouters.getRouterUrl(MyPublicServiceRouter.SEND_VERIFICATION_CODE_EMAIL) + "?email=" + email,String.class);
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
     * @param  methodName    方法名
     * @param  parameters    参数 格式：key1:base64(value1);key2:base64(value2) 如：routerId:MTAwMDE=
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

            if(parameters == null){
                responseEntity = restTemplate.getForEntity(routerUrl + "?" + USER_ID+ "=" + getUserId(),String.class);
            }else{
                if(isManager()){
                    //url后拼接的请求参数格式
                    String urlParameters = getUrlParameters(parameters);
                    routerUrl += urlParameters;
                    //请求参数
                    Map<String,Object> map = parseMap(parameters);
                    logger.info("get  methodName:" + methodName + ",url:" + routerUrl);
                    responseEntity = restTemplate.getForEntity(routerUrl,String.class,map);

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
    public ResponseEntity post(@RequestBody String json,String methodName,String parameters){
        logger.info("received post: json=" + json + ",methodName=" + methodName + ",parameters=" + parameters);
        if(StringUtils.isEmpty(methodName,parameters)){
            methodName = JsonUtils.getString(json,"methodName");
            parameters = JsonUtils.getString(json,"parameters");
        }
        if(StringUtils.isEmpty(methodName,parameters)){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_POST,methodName);
        if(routerUrl == null){
            logger.error("find no router:" + methodName);
            return MyResponse.forbidden();
        }
        ResponseEntity<String> responseEntity;
        try{
            Map<String,Object> map ;
            if(isManager()){
                routerUrl = routerUrl + getUrlParameters(parameters);
                map = parseMap(parameters);
                logger.info("manager post url:" + routerUrl);
            }else{
                routerUrl = routerUrl + getUrlParametersWithUserId(parameters);
                map = parseMapWithUserId(parameters);
            }
            logger.info("sendPost:  methodName:" + methodName + ",url:" + routerUrl + ",map:" + map);
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
    public ResponseEntity delete(@RequestBody String json,String methodName,String parameters){
        logger.info("received delete: json=" + json + ",methodName=" + methodName + ",parameters=" + parameters);
        if(StringUtils.isEmpty(methodName,parameters)){
            methodName = JsonUtils.getString(json,"methodName");
            parameters = JsonUtils.getString(json,"parameters");
        }
        if(StringUtils.isEmpty(methodName,parameters)){
            return MyResponse.badRequest();
        }
        String routerUrl = authenticateAndReturnRouterUrl(ConfigUtils.REQUEST_METHOD_DELETE,methodName);
        if(StringUtils.isEmpty(routerUrl)){
            return MyResponse.forbidden();
        }
        try{
            Map<String,Object> map ;
            if(isManager()){
                routerUrl = routerUrl + getUrlParameters(parameters);
                map = parseMap(parameters);
                logger.info("manager delete url:" + routerUrl);
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
        return MyResponse.ok(ResultData.success("删除成功"));
    }

    /**
     * PUT不允许空参数
     * @param methodName
     * @param parameters
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity put(@RequestBody String json, String methodName,String parameters){
        logger.info("received put: json=" + json + ",methodName=" + methodName + ",parameters=" + parameters);
        if(StringUtils.isEmpty(methodName,parameters)){
            methodName = JsonUtils.getString(json,"methodName");
            parameters = JsonUtils.getString(json,"parameters");
        }
        if(StringUtils.isEmpty(methodName,parameters)){
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
                logger.info("manager put url:" + routerUrl);
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
        return MyResponse.ok(ResultData.success("修改成功"));
    }

    /**
     * 鉴权
     * @param methodName 客户端调用的方法名
     * @return
     */
    private String authenticateAndReturnRouterUrl(String requestType,String methodName){
        Integer userId = getUserId();
        if(userId == null){
            return null;
        }
        List<Integer> routerIdList = CacheUtils.userRouterMap.get(getUserId());

        MyRouterObject routerObject = CacheUtils.getRouterMap().get(methodName);
        /*
         * 拿不到路由
         */
        if(routerObject == null || routerIdList == null || routerIdList.size() < 1){
            return null;
        }
        /*
         * 用户的请求类型和路由的请求类型不一致
         */
        if(!requestType.equals(routerObject.getRequestType())){
            return null;
        }

        Integer routerId = routerObject.getId();
        //判断用户是否有该路由权限
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
                ResultData resultData = ResultData.error("未知错误");
                response = ResponseEntity.status(exception.getStatusCode()).contentType(MediaType.APPLICATION_JSON).body(resultData);
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
        logger.info("setCacheForLogon :" + response);
        if(!MyResponseReader.isSuccess(response)){
            return;
        }
        int userId = MyResponseReader.getDataObject(response,"id",Integer.class);
        //将userId放入Session
        request.getSession().setAttribute("userId",userId);

        //查询用户的权限Id
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    MyRouters.getRouterUrl(MyUserManagerRouter.GET_ROUTER_IDS_BY_USER_ID)
                            + "?" + USER_ID +"=" + userId,String.class);

        logger.info("USER_MANAGER_PERMISSION_GET_ROUTER_IDS_BY_USER_ID response:" + responseEntity.getBody());
        if(!MyResponseReader.isSuccess(responseEntity)){
            return;
        }
        List<Integer> routerIds = MyResponseReader.getList(responseEntity,Integer.class);
        logger.info("routerIds:" + routerIds);
        //routerIds放入缓存
        CacheUtils.userRouterMap.put(userId,routerIds);
        //用户权限放入缓存
        responseEntity = restTemplate.getForEntity(
                    MyRouters.getRouterUrl(MyUserManagerRouter.GET_ROLE_BY_USER_ID)
                            + "?" + USER_ID +"=" + userId,String.class);
        if(!MyResponseReader.isSuccess(responseEntity)){
            return;
        }
        String roleName = MyResponseReader.getDataObject(responseEntity,"name",String.class);
        //角色名放入缓存
            CacheUtils.userRoleMap.put(userId,roleName);
            logger.info("logonWithUserName roleName :" + roleName + ", routerIds:" + routerIds);
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
