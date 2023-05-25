package com.example.ecommerce.dto;

import com.example.ecommerce.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String name;
    private String email;
    private Long phoneNumber;
    private String avatar;
    private String address;
    private ERole role;
}
