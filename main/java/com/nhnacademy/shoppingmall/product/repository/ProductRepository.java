package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.Product;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findByProductId(int productId);
    int saveProduct(Product product);
    int deleteByProductId(int productId) throws SQLException;
    int updateProduct(Product product);
    List<Product> getAllProductsList();

    int countByProductId(int productId);

}








