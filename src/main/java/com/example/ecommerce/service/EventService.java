package com.example.ecommerce.service;

import com.example.ecommerce.dto.EventDto;
import com.example.ecommerce.mapper.EventMapper;
import com.example.ecommerce.models.Event;
import com.example.ecommerce.models.EventImage;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repository.EventImageRepository;
import com.example.ecommerce.repository.EventRepository;
import com.example.ecommerce.utils.ImageUpload;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventImageRepository eventImageRepository;

    @Autowired
    private EventMapper eventMapper;

    @Autowired
    private ImageUpload imageUpload;

    @Autowired
    private AuthService authService;

    @Autowired
    private EntityManager entityManager;

    public List<EventDto> findAll() {
        return eventRepository.findAll().stream().map(event -> eventMapper.toDto(event))
                .collect(Collectors.toList());
    }

    public EventDto insert(MultipartFile[] files, Event event) throws IOException {
        User shop = authService.getCurrentUser();

        event.setShop(shop);

        Event newEvent = eventRepository.save(event);

        List<EventImage> images = new ArrayList<>();

        for (MultipartFile file : files) {
            String url = imageUpload.saveImageToS3(file);

            EventImage image = new EventImage(newEvent, url);

            eventImageRepository.save(image);
        }

        return eventMapper.toDto(newEvent);
    }

    public EventDto findById(Long id) {
        return eventMapper.toDto(eventRepository.findById(id).orElseThrow(() -> new RuntimeException()));
    }

    @Transactional
    public EventDto update(Long id, MultipartFile[] files, Event newEvent) throws IOException {
        Event oldEvent = eventRepository.findById(id).orElseThrow(() -> new RuntimeException());

        User shop = authService.getCurrentUser();

        newEvent.setShop(shop);

        List<EventImage> existingImages = oldEvent.getImages();
        existingImages.forEach(image -> entityManager.remove(image));

        for (MultipartFile file : files) {
            String url = imageUpload.saveImageToS3(file);

            EventImage image = new EventImage(oldEvent, url);

            eventImageRepository.save(image);
        }

        return eventMapper.toDto(eventRepository.save(eventMapper.update(oldEvent, newEvent)));
    }

    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException());
        for (EventImage image : event.getImages()) {
            eventImageRepository.delete(image);
        }
        eventRepository.deleteById(id);
    }

    public List<EventDto> findByShop() {
        User shop = authService.getCurrentUser();

        return eventRepository.findByShop(shop).stream().map(
                        event -> eventMapper.toDto(event))
                .collect(Collectors.toList());
    }
}
