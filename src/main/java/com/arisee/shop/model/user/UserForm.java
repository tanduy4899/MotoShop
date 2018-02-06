package com.arisee.shop.model.user;



import com.arisee.shop.domain.user.Role;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Data
public class UserForm {
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    private String confirmPassword;
    @NotBlank
    private String fullName;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
    @NotBlank
    private String sex;
    private String image;
    private Role roles;
//    private com.arisee.shop.model.user.role.Role roles;

}
