package com.guide.java.api.resource;

import com.guide.java.api.model.AuthenticationRequest;
import com.guide.java.api.model.User;
import com.guide.java.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserResource {

    private AuthenticationManager authenticationManager;

    private UserService userService;


    @RequestMapping(value = "user/check/token")
    public String token(){
        return "xxx";
    }


}
