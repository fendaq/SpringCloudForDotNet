package top.gongtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/6 14:36
 * @Description: Api 网关，使用端口5000
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(GatewayServiceApplication.class,args);
    }

}
