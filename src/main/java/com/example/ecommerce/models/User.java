package com.example.ecommerce.models;

import com.example.ecommerce.models.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseModel implements UserDetails {
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    private String avatar;
    private String address;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    addresses:[
//    {
//        country: {
//            type: String,
//        },
//        city:{
//            type: String,
//        },
//        address1:{
//            type: String,
//        },
//        address2:{
//            type: String,
//        },
//        zipCode:{
//            type: Number,
//        },
//        addressType:{
//            type: String,
//        },
//    }
//  ],
//    resetPasswordToken: String,
//    resetPasswordTime: Date,
}
