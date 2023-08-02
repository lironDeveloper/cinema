package com.cinema.galaxy.controllers;


import com.cinema.galaxy.DTOs.Review.ReviewDTO;
import com.cinema.galaxy.DTOs.User.UserDTO;
import com.cinema.galaxy.services.ReviewServiceImpl;
import com.cinema.galaxy.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
