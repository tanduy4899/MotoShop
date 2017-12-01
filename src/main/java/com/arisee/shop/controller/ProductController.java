package com.arisee.shop.controller;

import com.arisee.shop.domain.PagingObject;
import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.product.Product;
import com.arisee.shop.model.product.ProductForm;
import com.arisee.shop.model.user.User;
import com.arisee.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AbstractController.API + "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public PagingObject<Product> getAllUsers(Pageable pageable,
                                             @RequestParam(required = false, defaultValue = "") String name,
                                             @RequestParam(required = false, defaultValue = "") BigDecimal fistPrice,
                                             @RequestParam(required = false, defaultValue = "") BigDecimal lastPrice,
                                             @RequestParam(required = false, defaultValue = "") BigInteger id,
                                             @RequestParam(required = false, defaultValue = "") BigInteger categoryId) {
        return productService.getAllProducts(pageable, name, fistPrice, lastPrice, id);
    }

    @RequestMapping(value = ("/{id}"), method = RequestMethod.GET)
    public com.arisee.shop.model.product.Product getById(@PathVariable("id") BigInteger id) {
        return this.productService.getById(id).map(com.arisee.shop.domain.product.Product::toProduct).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(value = ("/{id}"), method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id) {
        this.productService.delete(id);
    }

    @RequestMapping(value = ("/{id}"), method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.arisee.shop.model.product.Product update(@PathVariable("id") BigInteger id, @Valid @RequestBody ProductForm productForm) {
        return this.productService.update(id, productForm).map(com.arisee.shop.domain.product.Product::toProduct).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.arisee.shop.model.product.Product insert(@Valid @RequestBody ProductForm productForm) {
        return this.productService.create(productForm).toProduct();
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public List<Product> getAllCategory(@PathVariable BigInteger categoryId) {
        return productService.getAllCategory(categoryId)
                .stream().map(com.arisee.shop.domain.product.Product::toProduct)
                .collect(Collectors.toList());
    }
}
