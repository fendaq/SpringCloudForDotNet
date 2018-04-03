package top.gongtao.jwt;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author: gongtao
 * @Date: Created in 2018/4/3 14:24
 * @Description: 负责存储角色和权限
 */
public class GrantedAuthorityImpl implements GrantedAuthority {
    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}

