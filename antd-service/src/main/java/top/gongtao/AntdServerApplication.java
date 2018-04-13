package top.gongtao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: gongtao
 * @Date: Created in 2018/2/6 14:32
 * @Description:
 */

//@SpringBootApplication(exclude = {
//        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
//        org.flowable.spring.boot.SecurityAutoConfiguration.class})



@SpringBootApplication
@EnableDiscoveryClient
public class AntdServerApplication implements CommandLineRunner {

//    @Autowired
//    UserRepository ur;
//
//    @Autowired
//    RoleRepository rr;
//
//    @Autowired
//    DepartmentRepository dr;

    public static void main(String[] args){
        SpringApplication.run(AntdServerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

//        User user1 = new User();
//        user1.setPassword("sdf");
//        user1.setRealname("sdfsd");
//        user1.setUsername("gongtao");
//
//        ur.save(user1);
//        Role role1 = new Role();
//        role1.setName("ROLE_ADMIN");
//        Role role2 = new Role();
//        role2.setName("ROLE_USER");
//
//        rr.save(role1);
//        rr.save(role2);
//        Department department = new Department("司令部","司令部描述", (long) 10,true);
//
//        dr.save(department);
//
//        user1.addRole(role1);
//        user1.addRole(role2);
//        user1.setDepartment(department);
//        ur.save(user1);




    }
}
