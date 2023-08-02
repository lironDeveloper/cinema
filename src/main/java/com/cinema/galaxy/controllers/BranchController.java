package com.cinema.galaxy.controllers;

import com.cinema.galaxy.DTOs.Branch.BranchDTO;
import com.cinema.galaxy.services.BranchServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
@RequiredArgsConstructor
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
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branch) {
        BranchDTO createdBranch = branchServiceImpl.createBranch(branch);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        if (branchServiceImpl.deleteBranch(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
