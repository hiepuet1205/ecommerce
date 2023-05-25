package com.example.ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupoun_code")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoupounCode extends BaseModel {
    private String name;
    private Long value;
    private Long minAmount;
    private Long maxAmount;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private User shop;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
