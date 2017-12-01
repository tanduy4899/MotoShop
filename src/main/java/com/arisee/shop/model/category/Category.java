package com.arisee.shop.model.category;

import com.arisee.shop.domain.product.Product;
import com.arisee.shop.model.product.ProductForm;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class Category {
    private BigInteger id;
    private String name;

}
