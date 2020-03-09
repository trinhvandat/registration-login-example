package com.spring.security.login.controller;


import com.spring.security.login.jwt.JwtTokenProvider;
import com.spring.security.login.model.CustomUserDetails;
import com.spring.security.login.payload.LoginRequest;
import com.spring.security.login.payload.LoginResponse;
import com.spring.security.login.payload.RandomStuff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        //xac thuc tu username va password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );


        //set thong tin authenticate vao security context
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //tra va jwt cho user
        String jwt = jwtTokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);


    }


    //api yeu cau phai xac thuc moi dc request
    @GetMapping("/random")
    public RandomStuff randomStuff(){
        return new RandomStuff("JWT hop le.");
    }

}
