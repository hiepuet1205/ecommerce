package com.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Item> cart;

    private String shippingAddress;

    private Long totalPrice;

    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
