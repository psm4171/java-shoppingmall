package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProductsList() {
        return productRepository.getAllProductsList();
    }


    @Override
    public Product addProduct(int productId) {
        log.debug("addProduct - 실행");
        Optional<Product> productOptional = productRepository.findByProductId(productId);
        return productOptional.orElse(null);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
        log.info("상품을 저장했습니다.");

    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
        log.info("상품을 업데이트했습니다.");
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        productRepository.deleteByProductId(productId);
        log.info("상품을 삭제했습니다.");
    }

}