package com.arisee.shop.domain.user;




import com.arisee.shop.model.user.RoleForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Data
@Table(name ="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String username;
    private String password;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String sex;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role roles ;

    public User() {

    }
    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.sex = user.getSex();
        this.roles = user.getRoles();
    }

    public com.arisee.shop.model.user.User toUser(){
        com.arisee.shop.model.user.User rs= new com.arisee.shop.model.user.User();
        rs.setId(id);
        rs.setUsername(username);
        rs.setPassword(password);
        rs.setFullName(fullName);
        rs.setAddress(address);
        rs.setPhone(phone);
        rs.setEmail(email);
        rs.setSex(sex);
        rs.setRoles(roles);
        return rs;
    }
}