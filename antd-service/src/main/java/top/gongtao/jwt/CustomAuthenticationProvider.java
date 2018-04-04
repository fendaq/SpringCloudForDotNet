package top.gongtao.jwt;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import top.gongtao.base.BaseServiceImpl;
import top.gongtao.dao.SysAdminUserDao;
import top.gongtao.entity.SysAdminUser;
import top.gongtao.service.SysAdminUserService;
import top.gongtao.util.FastJsonUtils;

import java.util.ArrayList;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 14:24
 * @Description: 自定义验证组件，提供密码验证功能
 */

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    SysAdminUserService sysAdminUserService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        SysAdminUser user = new SysAdminUser();
        user.setUsername(name);
        user.setPassword(password);

       // SysAdminUser sysAdminUser = sysAdminUserService.selectOne(user);


//        if(StringUtils.isBlank(user.getUsername())){
//            throw new BadCredentialsException("账号不能为空~");
//        }
//        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
//
//        BaseServiceImpl sysAdminUserService = new SysAdminUserService();
//        SysAdminUser adminUser = null;
//
//        if(adminUser == null){
//            throw new BadCredentialsException("密码错误~");
//        }
//
//        if(!adminUser.getStatus().equals(Byte.valueOf("1"))){
//            throw new BadCredentialsException("账号已被禁用~");
//        }
//        else{

            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
//        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}