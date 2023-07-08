package com.cinema.galaxy.controllers;

import com.cinema.galaxy.services.BranchService;
import com.cinema.galaxy.models.Branch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
public class BranchController {

    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService){
        this.branchService = branchService;
    }

    @GetMapping
    public List<Branch> getBranches() {
        return branchService.getBranches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable("id") Long id) {
        Branch branch = branchService.getBranchById(id);

        if(branch != null) {
            return ResponseEntity.ok(branch);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(@Valid @RequestBody Branch branch) {
        Branch createdBranch = branchService.createBranch(branch);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        if (branchService.deleteBranch(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
