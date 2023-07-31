package com.cinema.galaxy.controllers;


import com.cinema.galaxy.DTOs.ReviewDTO;
import com.cinema.galaxy.DTOs.UserCreationDTO;
import com.cinema.galaxy.DTOs.UserDTO;
import com.cinema.galaxy.services.ReviewServiceImpl;
import com.cinema.galaxy.services.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<UserDTO> getUsers() { return userServiceImpl.getAllUsers(); }

    @GetMapping("/{userId}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewDTO> reviews = reviewServiceImpl.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(reviews.getContent());
    }
}
