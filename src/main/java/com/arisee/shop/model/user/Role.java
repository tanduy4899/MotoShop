package com.arisee.shop.model.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigInteger;

@Data
public class Role {
    private BigInteger id;
    private String role;
}
