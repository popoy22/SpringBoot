package com.guide.java.api.resource;

import com.guide.java.api.dto.UserDTO;
import com.guide.java.api.request.AuthenticationRequest;
import com.guide.java.api.dto.AuthenticationResponseDTO;
import com.guide.java.api.model.User;
import com.guide.java.api.service.MyUserDetailsService;
import com.guide.java.api.service.UserService;
import com.guide.java.api.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserResource {


    @Autowired
    private JWTUtil jwtTokenUtil;

    @Autowired
    private  UserService userService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @RequestMapping(value = "users")
    public List<UserDTO> users(){
        return userService.loadAllUsers();
    }


    @RequestMapping(value = "user/seed/{count}")
    public List<User> seed(@PathVariable int count){
        return userService.seed(count);
    }

    @RequestMapping(value = "user/{id}")
    public UserDTO user(@PathVariable Long id){
        return userService.loadUser(id);
    }



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        boolean authenticated = myUserDetailsService.login(authenticationRequest.getEmail(),authenticationRequest.getPassword());
        if(authenticated){
            final User user = userService.loadUserByUsername(authenticationRequest.getEmail());
            final  String jwt = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
        }else{
            return  ResponseEntity.ok(new AuthenticationResponseDTO(null));
        }
    }







}
