package com.guide.java.api.service;
import com.guide.java.api.dto.UserDTO;
import com.guide.java.api.factory.UserFactory;
import com.guide.java.api.mapper.UserMapper;
import com.guide.java.api.model.User;
import com.guide.java.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFactory userFactory;

    UserMapper userMapper;

    public User loadUserByUsername(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> seed(Integer ctr){
        return  userFactory.seed(ctr);
    }

    public  List<UserDTO> loadAllUsers(){
        List<User> users =  userRepository.findAll();
        users = userRepository.findAllActiveUsersNative("popoy@yhmwhy.com");
        List<UserDTO> collect = users.stream().map(
                user -> userMapper.INSTANCE.userToUserDto(user)
        ).collect(Collectors.toList());
        return collect;
    }

    public UserDTO loadUser(Long id){
        User user = userRepository.getOne(id);

        return  userMapper.INSTANCE.userToUserDto(user);
    }


}
