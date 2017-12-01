package com.arisee.shop.controller;

import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.category.Category;
import com.arisee.shop.model.category.CategoryForm;
import com.arisee.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(AbstractController.API+"/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(method = RequestMethod.GET)
    public List<Category> getAll(){
        return this.categoryService.getAll();
    }
    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public com.arisee.shop.model.category.Category getById(@PathVariable("id") BigInteger id){
        return this.categoryService.getById(id).map(com.arisee.shop.domain.category.Category::toCategory).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.categoryService.delete(id);
    }

    @RequestMapping(value ="/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.arisee.shop.model.category.Category update(@PathVariable("id") BigInteger id, @Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.update(id,categoryForm).map(com.arisee.shop.domain.category.Category::toCategory).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public com.arisee.shop.model.category.Category insert(@Valid @RequestBody CategoryForm categoryForm){
        return this.categoryService.create(categoryForm).toCategory();
    }

}
