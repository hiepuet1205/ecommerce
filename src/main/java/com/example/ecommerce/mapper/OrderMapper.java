package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ItemDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.models.Item;
import com.example.ecommerce.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {
    @Autowired
    private ItemMapper itemMapper;

    public OrderDto toDto(Order order) {
        OrderDto orderDto = new OrderDto();

        if (order.getId() != null) {
            orderDto.setId(order.getId());
        }

        if (order.getShippingAddress() != null) {
            orderDto.setShippingAddress(order.getShippingAddress());
        }

        if (order.getTotalPrice() != null) {
            orderDto.setTotalPrice(order.getTotalPrice());
        }

        if (order.getStatus() != null) {
            orderDto.setStatus(order.getStatus());
        }

        if (order.getUser() != null) {
            orderDto.setUser(order.getUser().getName());
        }

        if (order.getCart() != null) {
            List<ItemDto> cart = new ArrayList<>();
            for (Item item : order.getCart()) {
                cart.add(itemMapper.toDto(item));
            }
            orderDto.setCart(cart);
        }

        return orderDto;
    }

    public Order toEntity(OrderDto orderDto) {
        Order order = new Order();

        if (orderDto.getId() != null) {
            order.setId(orderDto.getId());
        }

        if (orderDto.getShippingAddress() != null) {
            order.setShippingAddress(orderDto.getShippingAddress());
        }

        if (orderDto.getTotalPrice() != null) {
            order.setTotalPrice(orderDto.getTotalPrice());
        }

        if (orderDto.getStatus() != null) {
            order.setStatus(orderDto.getStatus());
        }

        return order;
    }

    public Order update(Order oldOrder, Order newOrder) {
        if (newOrder.getId() != null) {
            oldOrder.setId(newOrder.getId());
        }

        if (newOrder.getShippingAddress() != null) {
            oldOrder.setShippingAddress(newOrder.getShippingAddress());
        }

        if (newOrder.getTotalPrice() != null) {
            oldOrder.setTotalPrice(newOrder.getTotalPrice());
        }

        if (newOrder.getStatus() != null) {
            oldOrder.setStatus(newOrder.getStatus());
        }

        if (newOrder.getUser() != null) {
            oldOrder.setUser(newOrder.getUser());
        }

        return oldOrder;
    }
}
