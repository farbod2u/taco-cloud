package ir.farbod.tacocloud.service;

import ir.farbod.tacocloud.entity.Order;
import ir.farbod.tacocloud.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order entity){
        return orderRepository.save(entity);
    }

}
