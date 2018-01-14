package com.arisee.shop.domain.image;

import com.arisee.shop.domain.product.Product;
import com.arisee.shop.model.image.ImageForm;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public com.arisee.shop.model.image.Image toImageModel(){
        com.arisee.shop.model.image.Image image = new com.arisee.shop.model.image.Image();
        image.setId(id);
        image.setImageUrl(imageUrl);
        if(product !=null) image.setProductModel(product.toProduct());
        return image;
    }
    public ImageForm toImageForm(){
        ImageForm rs = new ImageForm();
        rs.setImageUrl(imageUrl);
//        rs.setProductId(product.getId());
        return rs;
    }

}
