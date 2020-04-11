package com.guide.java.api.repository;

import com.guide.java.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmailAndPassword(String email,String password);
    public User findByEmail(String email);
    public User findByUsername(String token);
    public List<User> findAll();
    public boolean existsByEmailAndPassword(String email,String password);

    @Query(value = "SELECT * FROM user u WHERE u.email = :email",nativeQuery = true)
    List<User> findAllActiveUsersNative(@Param("email") String email);
}
