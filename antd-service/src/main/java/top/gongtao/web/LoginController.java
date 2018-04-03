package top.gongtao.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gongtao.entity.SysAdminUser;
import top.gongtao.service.SysAdminUserService;
import top.gongtao.util.FastJsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 13:02
 * @Description: 登陆控制器
 */
@RestController
@RequestMapping("/api")
@Api(value = "LoginController", description="登录接口")
public class LoginController{

    @Autowired
    SysAdminUserService adminUserService;

    /**
     * 登录
     * @return
     */
    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "record", required=true, dataType = "SysAdminUser")	})
    @PostMapping(value = "/login/account", produces = {"application/json;charset=UTF-8"})
    public String login(@RequestBody SysAdminUser user) {
        Map<String, Object> data = new HashMap<String, Object>();
        if(StringUtils.isBlank(user.getUsername())){
            return FastJsonUtils.resultError(-100,"账号不能为空",null);
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));

        SysAdminUser adminUser = adminUserService.selectOne(user);

        if(adminUser == null){
            return FastJsonUtils.resultError(-100,"用户名密码错误",null);
        }

        if(!adminUser.getStatus().equals(Byte.valueOf("1"))){
            return FastJsonUtils.resultError(-100,"账号已被禁用",null);
        }

        data.put("userInfo", adminUser);
        return FastJsonUtils.resultSuccess(200,"登陆成功",data);
    }



}
