package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto extends BaseDto {
    private String name;
    private String description;
    private String category;
    private String tag;
    private Long originalPrice;
    private Long discountPrice;
    private Long stock;
    private Long soldOut;

    private List<String> images;
}
