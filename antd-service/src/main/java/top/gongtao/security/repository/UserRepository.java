package top.gongtao.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import top.gongtao.model.security.User;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/10 13:44
 * @Description:
 */

@RepositoryRestResource(collectionResourceRel = "user" , path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
