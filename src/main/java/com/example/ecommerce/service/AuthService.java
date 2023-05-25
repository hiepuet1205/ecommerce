package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.models.User;
import com.example.ecommerce.models.enums.ERole;
import com.example.ecommerce.payload.request.ChangePasswordRequest;
import com.example.ecommerce.payload.request.LoginRequest;
import com.example.ecommerce.payload.request.RegisterRequest;
import com.example.ecommerce.payload.response.JwtResponse;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ImageUpload imageUpload;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    public JwtResponse register(RegisterRequest registerRequest) throws IOException {
        User newUser = registerRequest.toUser();
        if (newUser.getRole() == null) {
            newUser.setRole(ERole.valueOf("ROLE_USER"));
        }
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        String url = imageUpload.saveImageToS3(registerRequest.getAvatar());
        newUser.setAvatar(url);

        userRepository.save(newUser);

        String token = jwtService.generateToken(newUser);

        return generateJwtResponse(token, newUser);
    }

    public JwtResponse authentication(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        User user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        return generateJwtResponse(jwt, user);
    }

    public JwtResponse changePassword(ChangePasswordRequest changePasswordRequest) {
        User user = getCurrentUser();

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password incorrect!");
        }

        String encodedNewPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        user.setPassword(encodedNewPassword);

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return generateJwtResponse(jwt, user);
    }

    public void updateAvatar(MultipartFile file) throws IOException {
        User user = getCurrentUser();

        String url = imageUpload.saveImageToS3(file);
        user.setAvatar(url);

        userRepository.save(user);
    }

    public UserDto updateUserInfo(UserDto userDto) throws IOException {
        User user = getCurrentUser();

        User newUser = userMapper.toEntity(userDto);

        return userMapper.toDto(userRepository.save(userMapper.update(user, newUser)));
    }

    private JwtResponse generateJwtResponse(String token, User user) {
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setToken(token);
        jwtResponse.setUser(userMapper.toDto(user));

        return jwtResponse;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public List<UserDto> getSeller() {
        return userRepository.findByRole(ERole.valueOf("ROLE_SHOP"))
                .stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    public UserDto getUser() {
        return userMapper.toDto(getCurrentUser());
    }
}
