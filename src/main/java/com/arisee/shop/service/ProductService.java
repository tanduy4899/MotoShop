package com.arisee.shop.service;

import com.arisee.shop.domain.PagingObject;
import com.arisee.shop.domain.category.Category;
import com.arisee.shop.domain.category.Category_;
import com.arisee.shop.domain.image.Image;
import com.arisee.shop.domain.product.Product_;
import com.arisee.shop.model.image.ImageForm;
import com.arisee.shop.model.product.Product;
import com.arisee.shop.model.product.ProductForm;
import com.arisee.shop.repository.ImageRepository;
import com.arisee.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.arisee.shop.domain.product.Product_.price;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageRepository imageRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.imageRepository = imageRepository;
    }

//    @PreAuthorize("isAuthenticated()")
    public Optional<com.arisee.shop.domain.product.Product> getById(BigInteger id) {
        return this.productRepository.getById(id);
    }

    public void delete(BigInteger id) {
        this.getById(id).ifPresent(this.productRepository::delete);
    }
    @Transactional
    public Optional<com.arisee.shop.domain.product.Product> update(BigInteger id, ProductForm productForm) {
        return this.getById(id).map(product -> {
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            product.setColor(productForm.getColor());
            product.setStatus(productForm.getStatus());
            product.setMadeIn(productForm.getMadeIn());
            product.setCategory(productForm.getCategory());

            product.getImages().clear();
            if(productForm.getImageForms() != null){
                for(ImageForm imageForm : productForm.getImageForms()){
                    com.arisee.shop.domain.image.Image image = imageRepository.findOne(id);
                    image.setProduct(product);
                    image.setImageUrl(imageForm.getImageUrl());
                    product.getImages().add(image);
                }
            }
            System.out.println(product.getImages().size());
            return this.productRepository.save(product);
        });
    }

    public com.arisee.shop.domain.product.Product create(ProductForm productForm) {
        com.arisee.shop.domain.product.Product product = new com.arisee.shop.domain.product.Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setColor(productForm.getColor());
        product.setStatus(productForm.getStatus());
        product.setMadeIn(productForm.getMadeIn());
        product.setCategory(productForm.getCategory());
        product.setListImage(productForm.getImageForms());
        return this.productRepository.save(product);
    }

    public List<com.arisee.shop.domain.product.Product> getAllCategory(BigInteger categoryId){
        List<com.arisee.shop.domain.product.Product> rs = new ArrayList<>();
        Category category = categoryService.getById(categoryId).orElse(null);
        if(category != null){
            rs = this.productRepository.getAllCategory(category);
        }

        return rs;
    }
    public PagingObject<Product> getAllProducts (Pageable pageable,String name, BigDecimal fistPrice, BigDecimal lastPrice , BigInteger id){
        if (pageable.getPageSize() >1000) throw new RuntimeException("Page size too big");
        PagingObject<Product> rs = new PagingObject<>();
        Page<com.arisee.shop.domain.product.Product> productPage;
        if(StringUtils.hasText(name) || StringUtils.isEmpty(fistPrice) || StringUtils.isEmpty(lastPrice) || StringUtils.isEmpty(id)){
            productPage = productRepository.findAll((root, query, cb) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.hasText(name)) {
                    predicates.add(cb.like(root.get(Product_.name), "%" + name + "%"));
                }
                if (fistPrice!=null && lastPrice!= null) {
                    final  Predicate pricePredicate;
                    pricePredicate = cb.and(
                            cb.greaterThanOrEqualTo(root.get(price),fistPrice),
                            cb.lessThanOrEqualTo(root.get(price),lastPrice));
                    predicates.add(pricePredicate);
                }

                if(id != null) {
                    final Join<com.arisee.shop.domain.product.Product, Category> categoryJoin = root.join(Product_.category);
                    predicates.add(cb.equal(categoryJoin.get(Category_.id), id));
                }
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }, pageable);
        }else {
            productPage = productRepository.findAll(pageable);
        }
        rs.setTotal(productPage.getTotalElements());
        rs.setTotalPage(productPage.getTotalPages());
        rs.setData(productPage.getContent().stream().map(com.arisee.shop.domain.product.Product::toProduct).collect(Collectors.toList()));

        return rs;
    }
}
