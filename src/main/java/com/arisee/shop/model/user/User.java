package com.arisee.shop.model.user;


import com.arisee.shop.domain.user.Role;
import com.arisee.shop.domain.user.UserRole;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Data
public class User {
    private BigInteger id;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String sex;
    private Set<Role> roles;
    private Set<com.arisee.shop.model.user.UserRole> userRoleList;
}
