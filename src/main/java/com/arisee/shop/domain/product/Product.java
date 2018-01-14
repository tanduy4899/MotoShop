package com.arisee.shop.domain.product;

import com.arisee.shop.domain.category.Category;
import com.arisee.shop.domain.image.Image;
import com.arisee.shop.model.image.ImageForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String name;
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    private String color;
    @Column(name = "made_in")
    private String madeIn;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> images = new ArrayList<>();

    public com.arisee.shop.model.product.Product toProduct(){
        com.arisee.shop.model.product.Product rs = new com.arisee.shop.model.product.Product();
        rs.setId(id);
        rs.setName(name);
        rs.setColor(color);
        rs.setPrice(price);
        rs.setStatus(status);
        rs.setMadeIn(madeIn);
        rs.setCategory(category);
        rs.setImageForms(getImages().stream().map(Image::toImageForm).collect(Collectors.toList()));
        return rs;
    }

    public void setListImage(List<ImageForm> listImageForms){
        images.clear();
        if(!CollectionUtils.isEmpty(listImageForms)){
            for(int i =0;i<listImageForms.size();i++){
                ImageForm imageForm = listImageForms.get(i);
                Image image = new Image();
                image.setProduct(this);
                image.setImageUrl(imageForm.getImageUrl());
                images.add(image);
            }
        }
    }

}
