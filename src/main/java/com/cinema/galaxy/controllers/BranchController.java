package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Branch.BranchCreationDTO;
import com.cinema.galaxy.DTOs.Branch.BranchDTO;
import com.cinema.galaxy.services.BranchServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class BranchController {
    private final BranchServiceImpl branchServiceImpl;

    @GetMapping
    public List<BranchDTO> getBranches() {
        return branchServiceImpl.getBranches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable("id") Long id) {
        BranchDTO branch = branchServiceImpl.getBranchById(id);

        if(branch != null) {
            return ResponseEntity.ok(branch);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchCreationDTO branchCreationDTO) {
        BranchDTO createdBranch = branchServiceImpl.createBranch(branchCreationDTO);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        if (branchServiceImpl.deleteBranch(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
