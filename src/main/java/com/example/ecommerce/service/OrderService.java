package com.example.ecommerce.service;

import com.example.ecommerce.dto.ItemDto;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.mapper.ItemMapper;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.models.Item;
import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private AuthService authService;

    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream().map(
                        order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public OrderDto findById(Long id) {
        return orderMapper.toDto(orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cant find order with id!")));
    }

    public List<OrderDto> findOrderOfUser() {
        User currentUser = authService.getCurrentUser();

        return orderRepository.findByUser(currentUser).stream().map(
                        order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public OrderDto findOrderOfUserById(Long id) {
        User currentUser = authService.getCurrentUser();

        Order order = orderRepository.findByUserAndId(currentUser, id);

        if (order == null) {
            throw new RuntimeException("Cant find order with id!");
        }

        return orderMapper.toDto(order);
    }

    public void deleteById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Cant find order with id!"));
        orderRepository.deleteById(id);
    }

    public OrderDto insert(OrderDto orderDto) {
        User currentUser = authService.getCurrentUser();

        Order order = orderMapper.toEntity(orderDto);

        orderRepository.save(order);

        for (ItemDto itemDto : orderDto.getCart()) {
            Item item = itemMapper.toEntity(itemDto);
            item.setOrder(order);
            itemRepository.save(item);
        }

        order.setUser(currentUser);

        return orderMapper.toDto(orderRepository.save(order));
    }

    public OrderDto updateStatus(Long id, String status) {
        User currentUser = authService.getCurrentUser();

        Order order = orderRepository.findByUserAndId(currentUser, id);

        order.setStatus(status);

        orderRepository.save(order);

        return orderMapper.toDto(order);
    }
}
