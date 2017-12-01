package com.arisee.shop.model.product;

import com.arisee.shop.domain.category.Category;
import com.arisee.shop.domain.product.ProductStatus;
import com.arisee.shop.model.image.Image;
import com.arisee.shop.model.image.ImageForm;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class Product {
    private BigInteger id;
    private String name;
    private BigDecimal price;
    private Category category;
    private ProductStatus status;
    private String color;
    private String madeIn;
    private List<ImageForm> imageForms;
}
