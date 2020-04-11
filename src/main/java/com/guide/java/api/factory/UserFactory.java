package com.guide.java.api.factory;

import com.github.javafaker.Faker;
import com.guide.java.api.dao.UserDAO;
import com.guide.java.api.model.User;
import com.guide.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserFactory {

    @Autowired
    private UserRepository userRepository;

    private  List<User> users;



    public List<User> seed(Integer count){

        List<User> allUser = new ArrayList<User>();
        Faker faker = new Faker();

        for (int i = 0; i <= count; i++) {
            User newUser = new User();
            newUser.setUsername(UUID.randomUUID().toString());
            newUser.setEmail(faker.internet().safeEmailAddress());
            newUser.setPassword("password");
            allUser.add(newUser);
        }

        userRepository.saveAll(allUser);
        return allUser;
    }

}
