package com.example.demo.commands.events;

import com.Userservice.Commonservice.commands.events.OrderCancelledEvent;
import com.Userservice.Commonservice.commands.events.OrderCompletedEvent;
import com.example.demo.commands.data.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import javax.persistence.criteria.Order;

@Component
public class OrderEventsHandler {
    private OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = new Order();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);

    }
    @EventHandler
    public void on(OrderCompletedEvent event) {
        Order order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        Order order
                = orderRepository.findById(event.getOrderId()).get();

        order.setOrderStatus(event.getOrderStatus());
        orderRepository.save(order);
    }
}
