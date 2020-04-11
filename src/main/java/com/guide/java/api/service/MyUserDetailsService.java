package com.guide.java.api.service;

import com.guide.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


// extending MyUserDetail to UserDetailsService
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
      return new User(userRepository.findByEmail(s).getEmail(),userRepository.findByEmail(s).getPassword(),new ArrayList<>());
    }

    public UserDetails loadUserByToken(String token) throws UsernameNotFoundException {
        return new User(userRepository.findByUsername(token).getUsername(),userRepository.findByUsername(token).getPassword(),new ArrayList<>());
    }




}
