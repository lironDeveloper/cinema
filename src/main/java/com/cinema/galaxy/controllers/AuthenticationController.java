package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.User.UserAuthenticationDTO;
import com.cinema.galaxy.DTOs.User.UserCreationDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.config.JwtUtils;
import com.cinema.galaxy.services.JwtUserDetailsService;
import com.cinema.galaxy.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtUtils jwtUtils;
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@Valid @RequestBody UserAuthenticationDTO userAuthenticationDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuthenticationDTO.getEmail(), userAuthenticationDTO.getPassword())
        );
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userAuthenticationDTO.getEmail());
        if(userDetails != null){
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserCreationDTO userCreationDTO) {
        UserDTO createdUser = userServiceImpl.createUser(userCreationDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
