package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.ProductImage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    public ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();

        if (product.getId() != null) {
            productDto.setId(product.getId());
        }

        if (product.getName() != null) {
            productDto.setName(product.getName());
        }

        if (product.getDescription() != null) {
            productDto.setDescription(product.getDescription());
        }

        if (product.getCategory() != null) {
            productDto.setCategory(product.getCategory());
        }

        if (product.getTag() != null) {
            productDto.setTag(product.getTag());
        }

        if (product.getOriginalPrice() != null) {
            productDto.setOriginalPrice(product.getOriginalPrice());
        }

        if (product.getDiscountPrice() != null) {
            productDto.setDiscountPrice(product.getDiscountPrice());
        }

        if (product.getStock() != null) {
            productDto.setStock(product.getStock());
        }

        if (product.getSoldOut() != null) {
            productDto.setSoldOut(product.getSoldOut());
        }

        if (product.getImages() != null) {
            List<String> imagePaths = new ArrayList<>();
            for (ProductImage image : product.getImages()) {
                imagePaths.add(image.getPath());
            }
            productDto.setImages(imagePaths);
        }

        return productDto;
    }

    ;

    public Product toEntity(ProductDto productDto) {
        Product product = new Product();

        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }

        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }

        if (productDto.getCategory() != null) {
            product.setCategory(productDto.getCategory());
        }

        if (productDto.getTag() != null) {
            product.setTag(productDto.getTag());
        }

        if (productDto.getOriginalPrice() != null) {
            product.setOriginalPrice(productDto.getOriginalPrice());
        }

        if (productDto.getDiscountPrice() != null) {
            product.setDiscountPrice(productDto.getDiscountPrice());
        }

        if (productDto.getStock() != null) {
            product.setStock(productDto.getStock());
        }

        if (productDto.getSoldOut() != null) {
            product.setSoldOut(productDto.getSoldOut());
        }

        return product;
    }

    ;

    public Product update(Product oldProduct, Product newProduct) {
        if (newProduct.getName() != null) {
            oldProduct.setName(newProduct.getName());
        }

        if (newProduct.getDescription() != null) {
            oldProduct.setDescription(newProduct.getDescription());
        }

        if (newProduct.getCategory() != null) {
            oldProduct.setCategory(newProduct.getCategory());
        }

        if (newProduct.getTag() != null) {
            oldProduct.setTag(newProduct.getTag());
        }

        if (newProduct.getOriginalPrice() != null) {
            oldProduct.setOriginalPrice(newProduct.getOriginalPrice());
        }

        if (newProduct.getDiscountPrice() != null) {
            oldProduct.setDiscountPrice(newProduct.getDiscountPrice());
        }

        if (newProduct.getStock() != null) {
            oldProduct.setStock(newProduct.getStock());
        }

        if (newProduct.getSoldOut() != null) {
            oldProduct.setSoldOut(newProduct.getSoldOut());
        }

        return oldProduct;
    }

    ;
}
