package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.models.ResponseObject;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Find all successful orders!", orderService.findAll()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Find successful order!", orderService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<ResponseObject> findOrderOfUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Find all successful orders!", orderService.findOrderOfUser()));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseObject> findOrderOfUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Find successful order!", orderService.findOrderOfUserById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id) {
        try {
            orderService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Delete successful order!", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user")
    public ResponseEntity<ResponseObject> insert(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Insert successful order!", orderService.insert(orderDto)));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/{id}/update-status-order")
    public ResponseEntity<ResponseObject> updateStatus(@PathVariable Long id, @RequestParam("status") String status) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Update successful order!", orderService.updateStatus(id, status)));
    }
}
