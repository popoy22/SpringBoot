package com.guide.java.api.service;
import com.guide.java.api.model.User;
import com.guide.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean login(String email, String password){
        return  userRepository.existsByEmailAndPassword(email,password);
    }

    public User loadUserByUsername(String email){
        return userRepository.findByEmail(email);
    }



}
