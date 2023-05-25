package com.example.ecommerce.service;

import com.example.ecommerce.dto.CoupounCodeDto;
import com.example.ecommerce.mapper.CoupounCodeMapper;
import com.example.ecommerce.models.CoupounCode;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.User;
import com.example.ecommerce.repository.CoupounCodeRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoupounCodeService {
    @Autowired
    private CoupounCodeRepository coupounCodeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private CoupounCodeMapper coupounCodeMapper;

    public List<CoupounCodeDto> findAll() {
        return coupounCodeRepository.findAll().stream().map(
                coupounCode -> coupounCodeMapper.toDto(coupounCode)
        ).collect(Collectors.toList());
    }

    public CoupounCodeDto insert(CoupounCodeDto coupounCodeDto) {
        User shop = authService.getCurrentUser();

        CoupounCode coupounCode = coupounCodeMapper.toEntity(coupounCodeDto);
        Product product = productRepository.findById(coupounCodeDto.getProduct())
                .orElseThrow(() -> new RuntimeException("Product does not exist!"));

        coupounCode.setProduct(product);
        coupounCode.setShop(shop);

        return coupounCodeMapper.toDto(coupounCodeRepository.save(coupounCode));
    }

    public CoupounCodeDto findById(Long id) {
        CoupounCode coupounCode = coupounCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoupounCode does not exist!"));

        return coupounCodeMapper.toDto(coupounCode);
    }

    public CoupounCodeDto update(Long id, CoupounCodeDto coupounCodeDto) {
        CoupounCode oldCoupounCode = coupounCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoupounCode does not exist!"));

        User shop = authService.getCurrentUser();

        CoupounCode newCoupounCode = coupounCodeMapper.toEntity(coupounCodeDto);
        Product product = productRepository.findById(coupounCodeDto.getProduct())
                .orElseThrow(() -> new RuntimeException("Product does not exist!"));

        newCoupounCode.setProduct(product);
        newCoupounCode.setShop(shop);

        return coupounCodeMapper.toDto(coupounCodeMapper.update(oldCoupounCode, newCoupounCode));
    }

    public void delete(Long id) {
        CoupounCode coupounCode = coupounCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CoupounCode does not exist!"));

        coupounCodeRepository.delete(coupounCode);
    }
}
