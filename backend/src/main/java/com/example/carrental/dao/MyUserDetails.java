package com.example.carrental.dao;

import com.example.carrental.entity.Costumer;
import com.example.carrental.entity.Role;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private Costumer costumer;
    @Transactional
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet = costumer.getRoles();

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();

        for (Role role : roleSet){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return costumer.getPassword();
    }

    @Override
    public String getUsername() {
        return costumer.getUsername();
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
