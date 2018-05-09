package top.gongtao.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: gongtao
 * @Date: Created in 2018/5/9 16:21
 * @Description:
 */

@Configuration
@Order(-20)
public class LoginConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .requestMatchers()
                .antMatchers("/","/login","/oauth/authorize","/oauth/confirm_access")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
