package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();

        if (user.getId() != null) {
            userDto.setId(user.getId());
        }

        if (user.getName() != null) {
            userDto.setName(user.getName());
        }

        if (user.getEmail() != null) {
            userDto.setEmail(user.getEmail());
        }

        if (user.getPhoneNumber() != null) {
            userDto.setPhoneNumber(user.getPhoneNumber());
        }

        if (user.getAvatar() != null) {
            userDto.setAvatar(user.getAvatar());
        }

        if (user.getAddress() != null) {
            userDto.setAddress(user.getAddress());
        }

        if (user.getRole() != null) {
            userDto.setRole(user.getRole());
        }

        return userDto;
    }

    public User toEntity(UserDto userDto) {
        User user = new User();

        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }

        if (userDto.getName() != null) {
            user.setName(userDto.getName());
        }

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }

        if (userDto.getPhoneNumber() != null) {
            user.setPhoneNumber(userDto.getPhoneNumber());
        }

        if (userDto.getAvatar() != null) {
            user.setAvatar(userDto.getAvatar());
        }

        if (userDto.getAddress() != null) {
            user.setAddress(userDto.getAddress());
        }

        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }

        return user;
    }

    public User update(User oldUser, User newUser) {
        if (newUser.getId() != null) {
            oldUser.setId(newUser.getId());
        }

        if (newUser.getName() != null) {
            oldUser.setName(newUser.getName());
        }

        if (newUser.getEmail() != null) {
            oldUser.setEmail(newUser.getEmail());
        }

        if (newUser.getPhoneNumber() != null) {
            oldUser.setPhoneNumber(newUser.getPhoneNumber());
        }

        if (newUser.getAvatar() != null) {
            oldUser.setAvatar(newUser.getAvatar());
        }

        if (newUser.getAddress() != null) {
            oldUser.setAddress(newUser.getAddress());
        }

        if (newUser.getRole() != null) {
            oldUser.setRole(newUser.getRole());
        }

        return oldUser;
    }
}
