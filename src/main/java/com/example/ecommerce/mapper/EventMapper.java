package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.EventDto;
import com.example.ecommerce.models.Event;
import com.example.ecommerce.models.EventImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {
    public EventDto toDto(Event event) {
        EventDto eventDto = new EventDto();

        if (event.getId() != null) {
            eventDto.setId(event.getId());
        }

        if (event.getName() != null) {
            eventDto.setName(event.getName());
        }

        if (event.getDescription() != null) {
            eventDto.setDescription(event.getDescription());
        }

        if (event.getCategory() != null) {
            eventDto.setCategory(event.getCategory());
        }

        if (event.getTags() != null) {
            eventDto.setTags(event.getTags());
        }

        if (event.getStartDate() != null) {
            eventDto.setStartDate(event.getStartDate());
        }

        if (event.getFinishDate() != null) {
            eventDto.setFinishDate(event.getFinishDate());
        }

        if (event.getStatus() != null) {
            eventDto.setStatus(event.getStatus());
        }

        if (event.getOriginalPrice() != null) {
            eventDto.setOriginalPrice(event.getOriginalPrice());
        }

        if (event.getDiscountPrice() != null) {
            eventDto.setDiscountPrice(event.getDiscountPrice());
        }

        if (event.getStock() != null) {
            eventDto.setStock(event.getStock());
        }

        if (event.getSoldOut() != null) {
            eventDto.setSoldOut(event.getSoldOut());
        }

        if (event.getShop() != null) {
            eventDto.setShop(event.getShop().getName());
        }

        if (event.getImages() != null) {
            List<String> imagePaths = new ArrayList<>();
            for (EventImage image : event.getImages()) {
                imagePaths.add(image.getPath());
            }
            eventDto.setImages(imagePaths);
        }

        return eventDto;
    }

    public Event toEntity(EventDto eventDto) {
        Event event = new Event();

        if (eventDto.getId() != null) {
            event.setId(eventDto.getId());
        }

        if (eventDto.getName() != null) {
            event.setName(eventDto.getName());
        }

        if (eventDto.getDescription() != null) {
            event.setDescription(eventDto.getDescription());
        }

        if (eventDto.getCategory() != null) {
            event.setCategory(eventDto.getCategory());
        }

        if (eventDto.getTags() != null) {
            event.setTags(eventDto.getTags());
        }

        if (eventDto.getStartDate() != null) {
            event.setStartDate(eventDto.getStartDate());
        }

        if (eventDto.getFinishDate() != null) {
            event.setFinishDate(eventDto.getFinishDate());
        }

        if (eventDto.getStatus() != null) {
            event.setStatus(eventDto.getStatus());
        }

        if (eventDto.getOriginalPrice() != null) {
            event.setOriginalPrice(eventDto.getOriginalPrice());
        }

        if (eventDto.getDiscountPrice() != null) {
            event.setDiscountPrice(eventDto.getDiscountPrice());
        }

        if (eventDto.getStock() != null) {
            event.setStock(eventDto.getStock());
        }

        if (eventDto.getSoldOut() != null) {
            event.setSoldOut(eventDto.getSoldOut());
        }

        return event;
    }

    public Event update(Event oldEvent, Event newEvent) {
        if (newEvent.getId() != null) {
            oldEvent.setId(newEvent.getId());
        }

        if (newEvent.getName() != null) {
            oldEvent.setName(newEvent.getName());
        }

        if (newEvent.getDescription() != null) {
            oldEvent.setDescription(newEvent.getDescription());
        }

        if (newEvent.getCategory() != null) {
            oldEvent.setCategory(newEvent.getCategory());
        }

        if (newEvent.getTags() != null) {
            oldEvent.setTags(newEvent.getTags());
        }

        if (newEvent.getStartDate() != null) {
            oldEvent.setStartDate(newEvent.getStartDate());
        }

        if (newEvent.getFinishDate() != null) {
            oldEvent.setFinishDate(newEvent.getFinishDate());
        }

        if (newEvent.getStatus() != null) {
            oldEvent.setStatus(newEvent.getStatus());
        }

        if (newEvent.getOriginalPrice() != null) {
            oldEvent.setOriginalPrice(newEvent.getOriginalPrice());
        }

        if (newEvent.getDiscountPrice() != null) {
            oldEvent.setDiscountPrice(newEvent.getDiscountPrice());
        }

        if (newEvent.getStock() != null) {
            oldEvent.setStock(newEvent.getStock());
        }

        if (newEvent.getSoldOut() != null) {
            oldEvent.setSoldOut(newEvent.getSoldOut());
        }

        if (newEvent.getShop() != null) {
            oldEvent.setShop(newEvent.getShop());
        }

        return oldEvent;
    }
}
