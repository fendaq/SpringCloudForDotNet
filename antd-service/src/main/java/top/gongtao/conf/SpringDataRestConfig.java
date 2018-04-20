package top.gongtao.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import top.gongtao.model.security.Authority;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/19 19:26
 * @Description: 解决 Spring Data Rest 不暴露 ID 字段的问题。 这里再 antd-design 前端的分页需要使用到唯一的 key 值
 */

@Configuration
public class SpringDataRestConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(){

        return new RepositoryRestConfigurerAdapter(){
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Authority.class);
            }
        };
    }


}
