package top.gongtao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.io.support.PropertySourceFactory;
import top.gongtao.entity.Role;
import top.gongtao.entity.User;
import top.gongtao.repository.RoleRepository;
import top.gongtao.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/6 14:32
 * @Description:
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AntdServerApplication implements CommandLineRunner {

    @Autowired
    UserRepository ur;

    @Autowired
    RoleRepository rr;

    public static void main(String[] args){
        SpringApplication.run(AntdServerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setPassword("sdf");
        user1.setRealname("sdfsd");
        user1.setUsername("gongtao");

        Role role1 = new Role();
        role1.setName("超级管理员");

        rr.save(role1);

        Set roles = new HashSet<Role>();

        roles.add(role1);
        user1.setRoles(roles);
        ur.save(user1);




    }
}
