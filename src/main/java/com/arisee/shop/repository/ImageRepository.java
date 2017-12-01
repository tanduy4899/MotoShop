package com.arisee.shop.repository;

import com.arisee.shop.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ImageRepository extends JpaRepository<Image,BigInteger>{
}
