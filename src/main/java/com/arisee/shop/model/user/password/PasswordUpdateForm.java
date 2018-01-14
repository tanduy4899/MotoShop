package com.arisee.shop.model.user.password;

import lombok.Data;

@Data
public class PasswordUpdateForm {
    private String password;
    private String confirmPassword;
    private String token;

}
