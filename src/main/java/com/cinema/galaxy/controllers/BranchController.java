package com.cinema.galaxy.controllers;

import com.cinema.galaxy.services.BranchServiceImpl;
import com.cinema.galaxy.models.Branch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
public class BranchController {
    @Autowired
    private BranchServiceImpl branchServiceImpl;

    @GetMapping
    public List<Branch> getBranches() {
        return branchServiceImpl.getBranches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable("id") Long id) {
        Branch branch = branchServiceImpl.getBranchById(id);

        if(branch != null) {
            return ResponseEntity.ok(branch);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(@Valid @RequestBody Branch branch) {
        Branch createdBranch = branchServiceImpl.createBranch(branch);
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
