package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private List<ItemDto> cart;

    private String shippingAddress;

    private Long totalPrice;

    private String status;

    private String user;
}
