package com.rookie.mall.demo.dto;

import com.rookie.mall.demo.entity.UmsAdmin;
import com.rookie.mall.demo.entity.UmsPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: chen
 * @date: 2022/1/21
 * @description: TODO 类描述
 **/
public class UserUtils implements UserDetails {

    private UmsAdmin umsAdmin;
    private List<UmsPermission> permissionList;

    /*
     * 账户是否没有过期
     */
    private boolean accountNonExpired;
    /*
     * 账户是否没有被锁定
     */
    private boolean accountNonLocked;
    /*
     * 密码是否没有过期
     */
    private boolean credentialsNonExpired;
    /*
     * 以及账户是否可用
     */
    private boolean enabled;

    public UserUtils(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (permissionList == null) {
            return authorities;
        }
        for (UmsPermission permission : permissionList) {
            authorities.add(new SimpleGrantedAuthority(permission.getValue()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
