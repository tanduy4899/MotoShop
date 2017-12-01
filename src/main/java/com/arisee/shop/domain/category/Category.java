package com.arisee.shop.domain.category;

import com.arisee.shop.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> productList = new ArrayList<>();

    public com.arisee.shop.model.category.Category toCategory(){
        com.arisee.shop.model.category.Category rs = new com.arisee.shop.model.category.Category();
        rs.setId(id);
        rs.setName(name);
        return rs;
    }
}
