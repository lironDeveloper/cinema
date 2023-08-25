package com.cinema.galaxy.controllers;


import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.enums.Role;
import com.cinema.galaxy.services.ReviewServiceImpl;
import com.cinema.galaxy.services.UserServiceImpl;
import com.cinema.galaxy.validators.enumValidator.ValidEnumValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<UserDTO> getUsers() { return userServiceImpl.getAllUsers(); }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO user = userServiceImpl.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewDTO> reviews = reviewServiceImpl.getReviewsByUserId(id, pageable);
        return ResponseEntity.ok(reviews.getContent());
    }

    @PutMapping("/update-role/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> changeUsersRole(
            @RequestParam(defaultValue = "ROLE_ADMIN") @ValidEnumValue(enumClass = Role.class, message = "יש לבחור תפקיד חוקי.") String role,
            @PathVariable Long id) {
        if(userServiceImpl.changeUsersRole(id, role)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
