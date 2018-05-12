package top.gongtao.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: gongtao
 * @Date: Created in 2018/5/9 15:50
 * @Description: 通过继承 AuthorizationServerConfigurerAdapter 并覆盖他的三个configure方法配置oauth
 */

public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthorizationServerProperties authorizationServerProperties;

    @Autowired
    private DataSource dataSource;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    /**
     * The OAuth2 tokens are defined in the datasource defined in the
     * <code>auth-server.yml</code> file stored in the Spring Cloud config
     * github repository.
     *
     * @return
     */
    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }



    /**
     * 这里配置客户端详情服务，在这里完成客户端信息的初始化，可以在这里把客户端信息写死，也可以从数据库中获取。
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                .withClient("demo")
//                .secret("demo")
//                .authorizedGrantTypes("password","authorization_code","refresh_token","implicit")
//                .scopes("read","write")
//                .accessTokenValiditySeconds((int) TimeUnit.HOURS.toSeconds(1))
//                .autoApprove(true);
        //从数据库中获取客户端信息
        clients.jdbc(dataSource)
                .passwordEncoder(passwordEncoder)
                .withClient("client")
                .authorizedGrantTypes("authorization_code", "client_credentials",
                        "refresh_token","password", "implicit")
                .authorities("ROLE_CLIENT")
                .resourceIds("apis")
                .scopes("read")
                .secret("secret")
                .accessTokenValiditySeconds(300);
    }

    /**
     * 这里配置令牌端点(Token Endpoint)的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess(authorizationServerProperties.getTokenKeyAccess());
        security.passwordEncoder(passwordEncoder);
    }

    /**
     * 这里配置授权(authorization) 以及令牌(token) 的访问端和令牌服务(token service)
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
        endpoints.authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager).tokenStore(tokenStore())
                .approvalStoreDisabled();
    }


    @Bean
    @ConfigurationProperties("jwt")
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }



    /**
     * Configure the {@link AuthenticationManagerBuilder} with initial
     * configuration to setup users.
     *
     * @author anilallewar
     *
     */
    @Configuration
    @Order(Ordered.LOWEST_PRECEDENCE - 20)
    protected static class AuthenticationManagerConfiguration extends
            GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        /**
         * Setup 2 users with different roles
         */
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth.jdbcAuthentication().dataSource(dataSource).withUser("dave")
                    .password("secret").roles("USER");
            auth.jdbcAuthentication().dataSource(dataSource).withUser("anil")
                    .password("password").roles("ADMIN");
            // @formatter:on
        }
    }
}
