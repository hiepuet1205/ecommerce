package com.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {
    @Column(unique = true)
    private String name;
    private String description;
    private String category;
    private String tag;
    private Long originalPrice;
    private Long discountPrice;
    private Long stock;
    private Long soldOut;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;
}
