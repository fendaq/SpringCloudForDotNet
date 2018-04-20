package top.gongtao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import top.gongtao.model.security.Authority;

import java.util.List;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/19 17:27
 * @Description: 基于 Spring Data Rest 的数据层
 */

@RepositoryRestResource(collectionResourceRel = "role" , path = "role")
public interface RoleRepository extends JpaRepository<Authority, Long> {

    @RestResource(path="nameStartWith",rel = "nameStartWith")
    List<Authority> findByNameStartsWith(@Param("name") String name);

    @RestResource(path="name",rel = "name")
    List<Authority> findByName(@Param("name") String name);





}
