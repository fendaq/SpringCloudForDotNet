package top.gongtao.feigndemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import top.gongtao.feigndemo.entity.User;
import top.gongtao.feigndemo.feign.UserFeignHystrixClient;

@RestController
public class FeignHystrixController {
  @Autowired
  private UserFeignHystrixClient userFeignHystrixClient;

  @GetMapping("feign/{id}")
  public User findByIdFeign(@PathVariable Long id) {
    User user = this.userFeignHystrixClient.findByIdFeign(id);
    return user;
  }
}
