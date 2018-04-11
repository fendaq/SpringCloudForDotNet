package top.gongtao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import top.gongtao.entity.User;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/10 13:44
 * @Description:
 */
//@RepositoryRestResource(collectionResourceRel = "user" , path = "user")
public interface UserRepository  {

    User findByUsername(String username);

}
