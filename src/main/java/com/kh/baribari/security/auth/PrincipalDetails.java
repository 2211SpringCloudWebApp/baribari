package com.kh.baribari.security.auth;

import com.kh.baribari.user.domain.Role;
import com.kh.baribari.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities;

    public PrincipalDetails(User user, List<Role> roles) {
        this.user = user;
        this.authorities = new ArrayList<>();
        for (Role role : roles) {
            this.authorities.add(new SimpleGrantedAuthority(role.getUserRole()));
        }
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getUserPw();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
