package com.arisee.shop.model.product;

import com.arisee.shop.domain.category.Category;
import com.arisee.shop.domain.product.ProductStatus;
import com.arisee.shop.model.image.Image;
import com.arisee.shop.model.image.ImageForm;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductForm {
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private ProductStatus status;
    @NotBlank
    private String color;
    @NotBlank
    private String madeIn;
    @NotNull
    private Category category;
    @NotEmpty
    private List<ImageForm> imageForms;
}
