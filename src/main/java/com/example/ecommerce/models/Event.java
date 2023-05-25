package com.example.ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseModel {
    @Column(unique = true)
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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> images;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private User shop;
}
