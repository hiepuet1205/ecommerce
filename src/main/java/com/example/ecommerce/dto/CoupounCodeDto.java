package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoupounCodeDto extends BaseDto {
    private String name;
    private Long value;
    private Long minAmount;
    private Long maxAmount;
    private String shop;
    private Long product;
}
