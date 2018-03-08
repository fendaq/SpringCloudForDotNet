
package top.gongtao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * @Author: gongtao
 * @Date: Created in 2018/3/8 09:21
 * @Description: 使用 Sidecar 整合非 JVM 微服务
 */

@SpringBootApplication
@EnableSidecar  // 启用 sidecar 异构  他是一个组合注解，包含 @EnableCircuitBreaker、@EnableDiscovery、@EnableZuulProxy
public class SidecarServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(SidecarServiceApplication.class,args);
    }

}
