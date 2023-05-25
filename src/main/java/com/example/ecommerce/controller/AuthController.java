package com.example.ecommerce.controller;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.models.ResponseObject;
import com.example.ecommerce.payload.request.ChangePasswordRequest;
import com.example.ecommerce.payload.request.LoginRequest;
import com.example.ecommerce.payload.request.RegisterRequest;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseObject> register(@ModelAttribute RegisterRequest registerRequest) throws IOException {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("fail", "User already exists!", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Register successfull!",
                            authService.register(registerRequest)));
        }
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ResponseObject> register(@RequestBody LoginRequest loginRequest) throws IOException {
        if (!userRepository.findByEmail(loginRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "User does not exists!", null));
        } else {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "success",
                                "Login successfull!",
                                authService.authentication(loginRequest)));
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("fail", "Password incorrect!", null));
            }
        }
    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<ResponseObject> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Change password successfull!",
                            authService.changePassword(changePasswordRequest)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("fail", "Old password incorrect!", null));
        }
    }

    @PatchMapping(value = "/update-avatar")
    public ResponseEntity<ResponseObject> updateAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        try {
            authService.updateAvatar(avatar);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Update avatar successfull!", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("fail", "Upload avatar fail!", null));
        }
    }

    @PutMapping(value = "/update-user-info")
    public ResponseEntity<ResponseObject> updateAvatar(@RequestBody UserDto userDto) throws IOException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Update avatar successfull!",
                            authService.updateUserInfo(userDto)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("fail", "Update fail!", e.getMessage()));
        }
    }

    @GetMapping(value = "/getSeller")
    public ResponseEntity<ResponseObject> getSeller() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Get seller successfull!",
                            authService.getSeller()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("fail", "Get seller fail!", e.getMessage()));
        }
    }

    @GetMapping(value = "getUser")
    public ResponseEntity<ResponseObject> getUser() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Get user successfull!",
                            authService.getUser()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("fail", "Get user fail!", e.getMessage()));
        }
    }
}
