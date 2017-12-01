package com.arisee.shop.model.user;



import com.arisee.shop.domain.user.Role;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
public class UserForm {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
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

    private Set<RoleForm> roleForms;

    private List<UserRole> userRoles;

}
