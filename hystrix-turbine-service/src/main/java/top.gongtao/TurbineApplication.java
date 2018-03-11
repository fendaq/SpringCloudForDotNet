package top.gongtao;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


/**
 * Created by gongtao on 2018/3/10 22:06.
 * 通过@EnableTurbine接口，激活对Turbine的支持。
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableTurbine
public class TurbineApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(top.gongtao.TurbineApplication.class).web(true).run(args);
    }
}