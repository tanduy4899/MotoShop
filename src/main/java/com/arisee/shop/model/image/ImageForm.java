package com.arisee.shop.model.image;

import com.arisee.shop.model.product.Product;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class ImageForm {
    @NotEmpty
    private String imageUrl;

}
