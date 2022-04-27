package com.zj.xyt.utils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JSONUtil {
    private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

    public static String returnJSONResult(boolean success, String state ,String message,Object entity){
        Map map = new HashMap();
        map.put("isSuccess",success);
        map.put("stateCode",state);
        map.put("message",message);
        map.put("entity",entity);
        logger.info("返回JSON字符串：" + JSONObject.fromObject(map).toString());
        return JSONObject.fromObject(map).toString();
    }

    /**
     * 设置失败消息JSON字符串
     * @param message
     * @return
     */
    public  static String returnFailReuslt(String message){
        return returnJSONResult(false, "200" ,message,null);
    }

    /**
     * 设置返回实体JSON字符串
     * @param entity
     * @return
     */
    public  static String returnEntityReuslt(Object entity){
        return returnJSONResult(true, "200" ,null,entity);
    }

    /**
     * 设置403权限不足JSON字符串
     * @return
     */
    public   static String returnForbiddenResult(){
        return returnJSONResult(false, "403" ,"权限不足!! 禁止访问!!",null);
    }

    /**
     * 返回成功消息JSON字符串
     * @param message
     * @return
     */
    public  static String returnSuccessResult(String message){
        return returnJSONResult(true, "200" ,message,null);
    }

}
