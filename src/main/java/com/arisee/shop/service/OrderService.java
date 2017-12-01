package com.arisee.shop.service;

import com.arisee.shop.model.order.Order;
import com.arisee.shop.model.order.OrderForm;
import com.arisee.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        List<com.arisee.shop.model.order.Order> rs = new ArrayList<>();
        this.orderRepository.findAll().forEach(order -> {
            rs.add(order.toOrder());
        });
        return rs;
    }

    public Optional<com.arisee.shop.domain.order.Order> getById(BigInteger id) {
        return this.orderRepository.getById(id);
    }

    public void delete(BigInteger id) {
        this.getById(id).ifPresent(orderRepository::delete);
    }

    public Optional<com.arisee.shop.domain.order.Order> update(BigInteger id, OrderForm orderForm) {
        return this.getById(id).map(order -> {
            order.setName(orderForm.getName());
            order.setCity(orderForm.getCity());
            order.setDistrict(orderForm.getDistrict());
            order.setWards(orderForm.getWards());
            order.setSpecificAddress(orderForm.getSpecificAddress());
            order.setPhone(orderForm.getPhone());
            order.setEmail(orderForm.getEmail());
            return this.orderRepository.save(order);
        });
    }

    public com.arisee.shop.domain.order.Order create(OrderForm orderForm) {
        com.arisee.shop.domain.order.Order order = new com.arisee.shop.domain.order.Order();
        order.setName(orderForm.getName());
        order.setCity(orderForm.getCity());
        order.setDistrict(orderForm.getDistrict());
        order.setWards(orderForm.getWards());
        order.setSpecificAddress(orderForm.getSpecificAddress());
        order.setPhone(orderForm.getPhone());
        order.setEmail(orderForm.getEmail());
        return this.orderRepository.save(order);
    }
}
