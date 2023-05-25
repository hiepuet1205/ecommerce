package com.example.ecommerce.controller;

import com.example.ecommerce.models.Event;
import com.example.ecommerce.models.ResponseObject;
import com.example.ecommerce.payload.request.EventRequest;
import com.example.ecommerce.repository.EventRepository;
import com.example.ecommerce.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/event")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Find all successful events!", eventService.findAll()));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<ResponseObject> insert(@ModelAttribute EventRequest eventRequest) throws IOException {
        try {
            Event event = eventRequest.toEvent();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Event insertion successfull!",
                            eventService.insert(eventRequest.getImages(), event)));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "Upload image fail!", null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Find event with id successful!", eventService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find event with this id!", null));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id, @ModelAttribute EventRequest eventRequest) {
        try {
            Event newEvent = eventRequest.toEvent();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Update event with id successful!",
                            eventService.update(id, eventRequest.getImages(), newEvent)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", e.getMessage(), null));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                    new ResponseObject("fail", "Upload image fail!", null));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id) {
        try {
            eventService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Delete event with id successful!", null
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find event with this id!", null));
        }
    }

    //    get all event of shop
    @GetMapping("/get-all-events")
    public ResponseEntity<ResponseObject> findByShop() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Find event with shop successful!", eventService.findByShop()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find event with this shop!", null));
        }
    }
}
