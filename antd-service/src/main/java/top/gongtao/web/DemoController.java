package top.gongtao.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gongtao.jwt.JSONResult;

import java.util.ArrayList;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 15:46
 * @Description: 仅供调试使用
 */

@RestController
public class DemoController {

    @GetMapping(value = "demo",produces="application/json;charset=UTF-8")
    public String demo(){

        ArrayList<String> users =  new ArrayList<String>(){{
            add("freewolf");
            add("tom");
            add("jerry");
        }};

        return JSONResult.fillResultString(200,"it's ok",users);
    }

}
