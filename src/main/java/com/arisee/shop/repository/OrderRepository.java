package com.arisee.shop.repository;

import com.arisee.shop.domain.order.Order;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,BigInteger> {
    Optional<Order> getById(BigInteger id);
}
