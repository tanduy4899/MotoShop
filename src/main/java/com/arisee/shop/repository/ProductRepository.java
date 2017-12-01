package com.arisee.shop.repository;


import com.arisee.shop.domain.category.Category;
import com.arisee.shop.domain.product.Product;
import com.arisee.shop.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, BigInteger>,JpaSpecificationExecutor<Product>{
    Optional<Product> getById(BigInteger id);

    @Query("SELECT u FROM #{#entityName} u WHERE u.category = ?1")
    List<Product> getAllCategory(Category category);
}
