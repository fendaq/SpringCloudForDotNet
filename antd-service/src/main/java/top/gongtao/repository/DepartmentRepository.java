package top.gongtao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import top.gongtao.entity.Department;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/11 10:26
 * @Description:
 */

@RepositoryRestResource(collectionResourceRel = "dept" , path = "dept")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
}
