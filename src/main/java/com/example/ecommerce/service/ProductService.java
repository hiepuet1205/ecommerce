package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.mapper.ProductMapper;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ProductImage;
import com.example.ecommerce.repository.ProductImageRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.utils.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private ImageUpload imageUpload;

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(product -> productMapper.toDto(product)).collect(Collectors.toList());
    }

    public ProductDto insert(MultipartFile[] files, Product product) throws IOException {
        Product newProduct = productRepository.save(product);

        for (MultipartFile file : files) {
            String url = imageUpload.saveImageToS3(file);

            ProductImage image = new ProductImage(newProduct, url);

            productImageRepository.save(image);
        }
        return productMapper.toDto(newProduct);
    }

    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(() -> new RuntimeException()));
    }

    public ProductDto update(Long id, MultipartFile[] files, Product newProduct) throws IOException {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException());

        productImageRepository.deleteAll(oldProduct.getImages());

        for (MultipartFile file : files) {
            String url = imageUpload.saveImageToS3(file);

            ProductImage image = new ProductImage(oldProduct, url);

            productImageRepository.save(image);
        }

        return productMapper.toDto(productRepository.save(productMapper.update(oldProduct, newProduct)));
    }

    public void deleteById(Long id) {
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException());
        for (ProductImage image : oldProduct.getImages()) {
            productImageRepository.delete(image);
        }
        productRepository.deleteById(id);
    }
}
