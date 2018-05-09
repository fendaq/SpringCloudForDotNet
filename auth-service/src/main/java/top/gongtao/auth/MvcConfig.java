package top.gongtao.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: gongtao
 * @Date: Created in 2018/5/9 15:46
 * @Description: 添加控制器视图配置
 */

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("login").setViewName("login");
        registry.addViewController("/").setViewName("index");
    }
}
