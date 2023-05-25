package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {
    private String name;
    private String description;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishDate;
    
    private String status;
    private String tags;
    private Long originalPrice;
    private Long discountPrice;
    private Long stock;
    private Long soldOut;

    private List<String> images;
    private String shop;
}
