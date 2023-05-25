package com.example.ecommerce.payload.request;

import com.example.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String category;
    private String tag;
    private Long originalPrice;
    private Long discountPrice;
    private Long stock;
    private Long soldOut;

    private MultipartFile[] images;

    public Product toProduct() {
        Product newProduct = new Product();
        newProduct.setName(this.getName());
        newProduct.setDescription(this.getDescription());
        newProduct.setCategory(this.getCategory());
        newProduct.setTag(this.getTag());
        newProduct.setOriginalPrice(this.getOriginalPrice());
        newProduct.setDiscountPrice(this.getDiscountPrice());
        newProduct.setStock(this.getStock());
        newProduct.setSoldOut(this.getSoldOut());

        return newProduct;
    }
}
