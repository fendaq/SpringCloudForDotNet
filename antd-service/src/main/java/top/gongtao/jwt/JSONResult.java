package top.gongtao.jwt;

import org.json.JSONObject;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 14:21
 * @Description:
 */
public class JSONResult{
    public static String fillResultString(Object status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};

        return jsonObject.toString();
    }
}
