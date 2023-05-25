package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ItemDto;
import com.example.ecommerce.models.Item;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();

        if (item.getId() != null) {
            itemDto.setId(item.getId());
        }

        if (item.getShop() != null) {
            itemDto.setShop(item.getShop().getName());
        }

        if (item.getOrder() != null) {
            itemDto.setOrder(item.getOrder().getId());
        }

        if (item.getProduct() != null) {
            itemDto.setProduct(item.getProduct().getName());
        }

        if (item.getQuantity() != null) {
            itemDto.setQuantity(item.getQuantity());
        }

        return itemDto;
    }

    public Item toEntity(ItemDto itemDto) {
        Item item = new Item();

        if (itemDto.getId() != null) {
            item.setId(itemDto.getId());
        }

        if (itemDto.getShop() != null) {
            item.setShop(userRepository.findByName(itemDto.getShop()));
        }

        if (itemDto.getProduct() != null) {
            item.setProduct(productRepository.findByName(itemDto.getProduct()));
        }

        if (itemDto.getQuantity() != null) {
            item.setQuantity(itemDto.getQuantity());
        }

        return item;
    }

    public Item update(Item oldItem, Item newItem) {
        if (newItem.getId() != null) {
            oldItem.setId(newItem.getId());
        }

        if (newItem.getShop() != null) {
            oldItem.setShop(newItem.getShop());
        }

        if (newItem.getOrder() != null) {
            oldItem.setOrder(newItem.getOrder());
        }

        if (newItem.getProduct() != null) {
            oldItem.setProduct(newItem.getProduct());
        }

        if (newItem.getQuantity() != null) {
            oldItem.setQuantity(newItem.getQuantity());
        }

        return oldItem;
    }
}
