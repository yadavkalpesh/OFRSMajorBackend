package com.ofrs.service;

import com.ofrs.model.RegisterUser;
import com.ofrs.repository.RegisterUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private RegisterUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println(username);
        RegisterUser user = repository.findByUserEmail(username);
        System.out.println("CustomUserDetSer "+user);
        return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), new ArrayList<>()); //ch
    }
}
