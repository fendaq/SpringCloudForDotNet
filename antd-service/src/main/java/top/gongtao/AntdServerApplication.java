package top.gongtao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/6 14:32
 * @Description:
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages={"top.gongtao.dao","com.framework.common.base"})
public class AntdServerApplication {

    public static void main(String[] args){
        SpringApplication.run(AntdServerApplication.class,args);
    }

}
