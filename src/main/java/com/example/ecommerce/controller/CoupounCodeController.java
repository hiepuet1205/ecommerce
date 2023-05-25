package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CoupounCodeDto;
import com.example.ecommerce.models.ResponseObject;
import com.example.ecommerce.service.CoupounCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/v1/coupoun-code")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CoupounCodeController {

    @Autowired
    private CoupounCodeService coupounCodeService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Find all coupouncode successful!",
                        coupounCodeService.findAll()));
    }

    @PreAuthorize("hasRole('SHOP')")
    @PostMapping("")
    public ResponseEntity<ResponseObject> insert(@RequestBody CoupounCodeDto coupounCodeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Create coupouncode successful!",
                            coupounCodeService.insert(coupounCodeDto)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Find coupouncode successful!",
                            coupounCodeService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('SHOP')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id, @RequestBody CoupounCodeDto coupounCodeDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Update coupouncode successful!",
                            coupounCodeService.update(id, coupounCodeDto)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('SHOP')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id) {
        try {
            coupounCodeService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Delete coupouncode successful!", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }
}
