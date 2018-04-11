package top.gongtao.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import top.gongtao.entity.User;
import top.gongtao.repository.UserRepository;

import java.util.ArrayList;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 14:24
 * @Description: 自定义验证组件，提供密码验证功能
 */

@Repository
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsername(name);



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