package com.example.ecommerce.payload.request;

import com.example.ecommerce.models.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
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

    private MultipartFile[] images;

    public Event toEvent() {
        Event newEvent = new Event();
        newEvent.setName(name);
        newEvent.setDescription(description);
        newEvent.setCategory(category);
        newEvent.setStartDate(startDate);
        newEvent.setFinishDate(finishDate);
        newEvent.setStatus(status);
        newEvent.setTags(tags);
        newEvent.setOriginalPrice(originalPrice);
        newEvent.setDiscountPrice(discountPrice);
        newEvent.setStock(stock);
        newEvent.setSoldOut(soldOut);

        return newEvent;
    }
}
