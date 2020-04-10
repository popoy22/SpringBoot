package com.guide.java.api.resource;

import com.guide.java.api.model.AuthenticationRequest;
import com.guide.java.api.model.AuthenticationResponse;
import com.guide.java.api.service.MyUserDetailsService;
import com.guide.java.api.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JWTUtil jwtTokenUtil;

    @RequestMapping(value = "user/check/token")
    public String token(){
        return "xxx";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
       try{
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword())
           );
       } catch (BadCredentialsException e){
           throw new Exception("Incorrect username or password",e);

       }
       final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
       final  String jwt = jwtTokenUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticationResponse(jwt));
   }


}
