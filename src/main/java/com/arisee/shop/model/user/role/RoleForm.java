package com.arisee.shop.model.user.role;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RoleForm {
    @NotBlank
    private String role;
}
