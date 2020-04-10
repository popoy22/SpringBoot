package com.guide.java.api.repository;

import com.guide.java.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmailAndPassword(String email,String password);
    public User findByEmail(String email);
    public boolean existsByEmailAndPassword(String email,String password);

}
