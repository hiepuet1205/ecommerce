package com.example.ecommerce.controller;

import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ResponseObject;
import com.example.ecommerce.payload.request.ProductRequest;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/product")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Find all successful products!", productService.findAll()));
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<ResponseObject> insert(@ModelAttribute ProductRequest productRequest) throws IOException {
        if (productRepository.findByName(productRequest.getName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseObject("fail", "Product insertion failed!", null));
        } else {
            Product newProduct = productRequest.toProduct();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Product insertion successfull!", productService.insert(productRequest.getImages(), newProduct)));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("success", "Find product with id successful!", productService.findById(id)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("fail", "Cant find product with this id!", null));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP')")
    public ResponseEntity<ResponseObject> update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        try {
            Product newProduct = productRequest.toProduct();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject(
                            "success",
                            "Update product with id successful!",
                            productService.update(id, productRequest.getImages(), newProduct)));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find product with this id!", null));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Upload image fail!", null));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseObject> deleteById(@PathVariable Long id) {
        try {
            productService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Delete product with id successful!", null
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "Cant find product with this id!", null));
        }
    }
}
