package top.gongtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/7 14:40
 * @Description: 服务客户端示例
 */

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaClientApplication.class,args);
    }

}
