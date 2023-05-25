package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.CoupounCodeDto;
import com.example.ecommerce.models.CoupounCode;
import org.springframework.stereotype.Component;

@Component
public class CoupounCodeMapper {
    public CoupounCodeDto toDto(CoupounCode coupounCode) {
        CoupounCodeDto coupounCodeDto = new CoupounCodeDto();

        if (coupounCode.getId() != null) {
            coupounCodeDto.setId(coupounCode.getId());
        }

        if (coupounCode.getName() != null) {
            coupounCodeDto.setName(coupounCode.getName());
        }

        if (coupounCode.getValue() != null) {
            coupounCodeDto.setValue(coupounCode.getValue());
        }

        if (coupounCode.getMinAmount() != null) {
            coupounCodeDto.setMinAmount(coupounCode.getMinAmount());
        }

        if (coupounCode.getMaxAmount() != null) {
            coupounCodeDto.setMaxAmount(coupounCode.getMaxAmount());
        }

        if (coupounCode.getShop() != null) {
            coupounCodeDto.setShop(coupounCode.getShop().getName());
        }

        if (coupounCode.getProduct() != null) {
            coupounCodeDto.setProduct(coupounCode.getProduct().getId());
        }

        return coupounCodeDto;
    }

    public CoupounCode toEntity(CoupounCodeDto coupounCodeDto) {
        CoupounCode coupounCode = new CoupounCode();

        if (coupounCodeDto.getId() != null) {
            coupounCode.setId(coupounCodeDto.getId());
        }

        if (coupounCodeDto.getName() != null) {
            coupounCode.setName(coupounCodeDto.getName());
        }

        if (coupounCodeDto.getValue() != null) {
            coupounCode.setValue(coupounCodeDto.getValue());
        }

        if (coupounCodeDto.getMinAmount() != null) {
            coupounCode.setMinAmount(coupounCodeDto.getMinAmount());
        }

        if (coupounCodeDto.getMaxAmount() != null) {
            coupounCode.setMaxAmount(coupounCodeDto.getMaxAmount());
        }

        return coupounCode;
    }

    public CoupounCode update(CoupounCode oldCoupounCode, CoupounCode newCoupounCode) {
        if (newCoupounCode.getId() != null) {
            oldCoupounCode.setId(newCoupounCode.getId());
        }

        if (newCoupounCode.getName() != null) {
            oldCoupounCode.setName(newCoupounCode.getName());
        }

        if (newCoupounCode.getValue() != null) {
            oldCoupounCode.setValue(newCoupounCode.getValue());
        }

        if (newCoupounCode.getMinAmount() != null) {
            oldCoupounCode.setMinAmount(newCoupounCode.getMinAmount());
        }

        if (newCoupounCode.getMaxAmount() != null) {
            oldCoupounCode.setMaxAmount(newCoupounCode.getMaxAmount());
        }

        if (newCoupounCode.getShop() != null) {
            oldCoupounCode.setShop(newCoupounCode.getShop());
        }

        if (newCoupounCode.getProduct() != null) {
            oldCoupounCode.setProduct(newCoupounCode.getProduct());
        }

        return oldCoupounCode;
    }
}
