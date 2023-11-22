package com.example.carrental.dao;

import com.example.carrental.entity.Costumer;
import com.example.carrental.repository.CostumerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {
    private CostumerRepository costumerRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Costumer costumer = costumerRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() ->
                new RuntimeException("Costumer with username or email: " + usernameOrEmail + " was not found!"));

        return null;
    }
}
