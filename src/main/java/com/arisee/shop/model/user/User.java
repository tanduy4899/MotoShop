package com.arisee.shop.model.user;

import com.arisee.shop.domain.user.Role;
import lombok.Data;

import javax.persistence.Column;
import java.math.BigInteger;


@Data
public class User {
    private BigInteger id;
    @Column(unique = true)
    private String username;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String sex;
    private Role roles;
}
