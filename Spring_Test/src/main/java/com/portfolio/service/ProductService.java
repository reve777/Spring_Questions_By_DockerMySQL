package com.portfolio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.entity.Product;
import com.portfolio.repository.ProductPriceRepository;
import com.portfolio.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    // 創建或更新商品
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // 根據 ID 查找商品
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    // 查找所有商品
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // 根據 ID 刪除商品
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // 其他自定義業務邏輯可以寫在這裡
}
