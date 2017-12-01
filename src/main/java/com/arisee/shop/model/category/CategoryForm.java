package com.arisee.shop.model.category;

import com.arisee.shop.model.product.ProductForm;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Data
public class CategoryForm {
    @NotBlank
    private String name;
}
