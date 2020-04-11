package com.guide.java.api.service;

import com.guide.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

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

    public boolean login(String email, String password){
        if(userRepository.existsByEmailAndPassword(email,password)){
            com.guide.java.api.model.User user = userRepository.findByEmailAndPassword(email,password);
            user.setUsername(UUID.randomUUID().toString());
            userRepository.save(user);
        }
        return  userRepository.existsByEmailAndPassword(email,password);
    }



}
