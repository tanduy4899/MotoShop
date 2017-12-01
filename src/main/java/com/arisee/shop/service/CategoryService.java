package com.arisee.shop.service;


import com.arisee.shop.model.category.Category;
import com.arisee.shop.model.category.CategoryForm;
import com.arisee.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll(){
        List<Category> rs = new ArrayList<>();
        this.categoryRepository.findAll().forEach(category -> {
            rs.add(category.toCategory());
        });
        return rs;
    }

    public Optional<com.arisee.shop.domain.category.Category> getById(BigInteger id){
        return this.categoryRepository.getById(id);
    }
    public void delete(BigInteger id){
        this.getById(id).ifPresent(this.categoryRepository::delete);
    }
    public Optional<com.arisee.shop.domain.category.Category> update(BigInteger id, CategoryForm categoryForm){
        return this.getById(id).map(category -> {
            category.setName(categoryForm.getName());
            return this.categoryRepository.save(category);
        });
    }
    public com.arisee.shop.domain.category.Category create(CategoryForm categoryForm){
        com.arisee.shop.domain.category.Category category = new com.arisee.shop.domain.category.Category();
        category.setName(categoryForm.getName());
        return this.categoryRepository.save(category);
    }
}
