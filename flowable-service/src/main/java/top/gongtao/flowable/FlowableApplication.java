package top.gongtao.flowable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.flowable.spring.boot.SecurityAutoConfiguration.class
})
public class FlowableApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowableApplication.class, args);
	}



	@Bean
	public DataSource database() {
//		return DataSourceBuilder.create()
//				.url("jdbc:mysql://localhost/flowablesbrest?characterEncoding=UTF-8")
//				.username("root")
//				.password("1111")
//				.driverClassName("com.mysql.jdbc.Driver")
//				.build();
		return DataSourceBuilder.create()
				.url("jdbc:mysql://192.168.16.101:3306/flowablerest?characterEncoding=UTF-8")
				.username("root")
				.password("t6y7@u8")
				.driverClassName("com.mysql.jdbc.Driver")
				.build();
	}
}
