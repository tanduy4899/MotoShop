package com.arisee.shop.model.order;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Order {
    private BigInteger id;
    private String name;
    private String city;
    private String district;
    private String wards;
    private String specificAddress;
    private String phone;
    private String email;
}
