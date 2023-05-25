package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto extends BaseDto {
    private Long order;

    private String shop;

    private String product;

    private Long quantity;
}
