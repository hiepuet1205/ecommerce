package com.example.ecommerce.payload.request;

import com.example.ecommerce.models.User;
import com.example.ecommerce.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Long phoneNumber;
    private MultipartFile avatar;
    private String role;

    public User toUser() {
        User user = new User();
        user.setName(this.getName());
        user.setEmail(this.getEmail());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setRole(ERole.valueOf(role));

        return user;
    }
}
