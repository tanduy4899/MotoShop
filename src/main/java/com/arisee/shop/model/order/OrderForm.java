package com.arisee.shop.model.order;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class OrderForm {
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String wards;
    @NotBlank
    private String specificAddress;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;
}

