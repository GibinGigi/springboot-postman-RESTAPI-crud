package com.auth2.Service;

import java.util.HashSet;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth2.Model.ApplicationUser;
import com.auth2.Model.LoginResponseDTO;
import com.auth2.Model.Role;
import com.auth2.Repository.RoleRepository;
import com.auth2.Repository.UserRepository;



@Service
@Transactional
public class AuthenticationService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private TokenService tokenService;


    public ApplicationUser registerUser(String username, String password){


        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();


        Set<Role> authorities = new HashSet<>();


        authorities.add(userRole);


        return userRepository.save(new ApplicationUser(0, username, encodedPassword, authorities));
    }


    public LoginResponseDTO loginUser(String username, String password){


        try{
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );


            String token = tokenService.generateJwt(auth);


            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);


        } catch(AuthenticationException e){
            return new LoginResponseDTO(null, "");
        }
    }


}