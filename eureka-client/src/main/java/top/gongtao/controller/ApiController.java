package top.gongtao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/7 14:43
 * @Description:
 */

@RestController
public class ApiController {

    @GetMapping("hello")
    public String getString(){
        return "Hello MicroService";
    }

}
