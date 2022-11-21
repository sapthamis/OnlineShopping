package com.microservices.demo.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.demo.dto.JWTAuthResponse;
import com.microservices.demo.dto.LoginDto;
import com.microservices.demo.dto.SignUpDto;
import com.microservices.demo.entity.Role;
import com.microservices.demo.entity.User;
import com.microservices.demo.repository.RoleRepository;
import com.microservices.demo.repository.UserRepository;
import com.microservices.demo.security.JwtTokenProvider;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController{

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private RoleRepository roleRepository;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private JwtTokenProvider tokenProvider;

	    @PostMapping("/signin")
	    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){
	        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
	        // generate token from token provider
	        String token = tokenProvider.generateToken(authentication);
	        return ResponseEntity.ok(new JWTAuthResponse(token));
//	        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
	    }

	    @PostMapping("/signup")
	    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

	        // add check for username exists in a DB
	        if(userRepository.existsByUsername(signUpDto.getUsername())){
	            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
	        }

	        // add check for email exists in DB
	        if(userRepository.existsByEmail(signUpDto.getEmail())){
	            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
	        }

	        // create user object
	        User user = new User();
	        user.setName(signUpDto.getName());
	        user.setUsername(signUpDto.getUsername());
	        user.setEmail(signUpDto.getEmail());
	        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

	        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
	        user.setRoles(Collections.singleton(roles));

	        userRepository.save(user);

	        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	    }
	
}