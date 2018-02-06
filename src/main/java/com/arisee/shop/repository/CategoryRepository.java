package com.arisee.shop.repository;


import com.arisee.shop.domain.category.Category;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,BigInteger> {
    Optional<Category> getById(BigInteger id);
}

