package com.arisee.shop.domain.order;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name ="`orders`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private String city;
    private String district;
    private String wards;
    private String specificAddress;
    private String phone;
    private String email;


    public com.arisee.shop.model.order.Order toOrder(){
        com.arisee.shop.model.order.Order rs = new com.arisee.shop.model.order.Order();
        rs.setId(id);
        rs.setName(name);
        rs.setCity(city);
        rs.setDistrict(district);
        rs.setWards(wards);
        rs.setSpecificAddress(specificAddress);
        rs.setPhone(phone);
        rs.setEmail(email);
        return rs;
    }
}
