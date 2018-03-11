package top.gongtao.feigndemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/7 14:43
 * @Description:
 */

@RestController
public class ApiController {

    @Autowired
    SampleService sampleService;

    @GetMapping("hello")
    public String getString(){
        return sampleService.findById((long) 1).toString();
    }

}
