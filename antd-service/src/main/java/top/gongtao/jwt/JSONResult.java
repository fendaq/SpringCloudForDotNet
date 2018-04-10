package top.gongtao.jwt;

import org.json.JSONObject;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 14:21
 * @Description:
 */
public class JSONResult{
    public static String fillResultString2(Object status, String message, Object result){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
            put("currentAuthority", "admin");
        }};

        return jsonObject.toString();
    }

    public static String fillResultString(String status, String message, String currentAuthority ,String result,String type){
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("type", type);
            put("result", result);
            put("currentAuthority", currentAuthority);
        }};

        return jsonObject.toString();
    }
}
