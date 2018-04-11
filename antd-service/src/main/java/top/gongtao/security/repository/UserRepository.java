package top.gongtao.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import top.gongtao.model.security.User;

/**
 * Created by stephan on 20.03.16.
 */
@RepositoryRestResource(collectionResourceRel = "user" , path = "user")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
