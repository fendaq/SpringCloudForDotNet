package top.gongtao.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import top.gongtao.entity.SysAdminUser;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/4 09:01
 * @Description: 用户接口
 */

@RestController
@RequestMapping("/api")
@Api(value = "UserController", description="用户接口")
public class UserController {

    @ApiOperation(value = "获取当前登陆用户", notes = "获取当前登陆用户")
    @GetMapping(value = "/currentUser", produces = {"application/json;charset=UTF-8"})
    public String getCurrentUser() {

//        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


//        String userName = userDetails.getUsername();

        return authentication.toString();
    }


}
