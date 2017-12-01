package com.arisee.shop.controller;

import com.arisee.shop.exception.NotFoundException;
import com.arisee.shop.model.order.Order;
import com.arisee.shop.model.order.OrderForm;
import com.arisee.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(AbstractController.API+"/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Order> getAll(){
        return this.orderService.getAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public com.arisee.shop.model.order.Order getById(@PathVariable("id") BigInteger id){
        return this.orderService.getById(id).map(com.arisee.shop.domain.order.Order::toOrder).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") BigInteger id){
        this.orderService.delete(id);
    }
    @RequestMapping(value = "/id", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order update(@PathVariable("id") BigInteger id,@Valid @RequestBody OrderForm orderForm){
        return this.orderService.update(id,orderForm).map(com.arisee.shop.domain.order.Order::toOrder).orElseThrow(NotFoundException::new);
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order insert(@Valid @RequestBody OrderForm orderForm){
        return this.orderService.create(orderForm).toOrder();
    }
}
