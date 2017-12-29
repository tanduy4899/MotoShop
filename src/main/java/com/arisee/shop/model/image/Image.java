package com.arisee.shop.model.image;


import com.arisee.shop.model.product.Product;
import lombok.Data;
import java.math.BigInteger;

@Data
public class Image {
    private BigInteger id;
    private String imageUrl;
    private Product productModel;

}
