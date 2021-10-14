package com.example.study.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class AdminUserDetails implements UserDetails {

    private SysUser sysUser;
    private List<SysResource> sysResources;

    public AdminUserDetails(SysUser sysUser, List<SysResource> sysResources) {
        this.sysUser = sysUser;
        this.sysResources = sysResources;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (SysResource privilege : sysResources) {
            grantedAuthorities.add(new SimpleGrantedAuthority(privilege.getId() + ":" + privilege.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return sysUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return sysUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return sysUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return sysUser.isEnabled();
    }
}
