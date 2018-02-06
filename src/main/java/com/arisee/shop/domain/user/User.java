package com.arisee.shop.domain.user;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;


@Entity
@Data
@Table(name ="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "username",unique = true)
    private String username;
    private String password;
    @Column(name = "confirm_password")
    private String confirmPassword;
    @Column(name = "full_name")
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String sex;
    private String image;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roles ;

//    @OneToOne(mappedBy = "user")
//    private UserToken userToken;

    public User() {

    }
    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.confirmPassword=user.getConfirmPassword();
        this.fullName = user.getFullName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.sex = user.getSex();
        this.roles = user.getRoles();
        this.image = user.getImage();

    }

    public com.arisee.shop.model.user.User toUser(){
        com.arisee.shop.model.user.User rs= new com.arisee.shop.model.user.User();
        rs.setId(id);
        rs.setUsername(username);
        rs.setPassword(password);
        rs.setConfirmPassword(confirmPassword);
        rs.setFullName(fullName);
        rs.setAddress(address);
        rs.setPhone(phone);
        rs.setEmail(email);
        rs.setSex(sex);
        rs.setImage(image);
        rs.setRoles(roles);
        return rs;
    }






}