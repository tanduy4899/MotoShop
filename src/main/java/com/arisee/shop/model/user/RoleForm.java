package com.arisee.shop.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RoleForm {
    @NotBlank
    private String role;
}
