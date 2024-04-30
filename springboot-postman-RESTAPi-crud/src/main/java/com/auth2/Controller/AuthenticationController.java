package com.auth2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth2.Model.ApplicationUser;
import com.auth2.Model.LoginResponseDTO;
import com.auth2.Model.RegistrationDTO;
import com.auth2.Service.AuthenticationService;

@RestController
@CrossOrigin("http://localhost:3002")
@RequestMapping("/auth")




public class AuthenticationController {


    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }
   
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}  

