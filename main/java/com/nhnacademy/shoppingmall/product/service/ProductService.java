package com.nhnacademy.shoppingmall.product.service;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> getAllProductsList();

    Product addProduct(int productId);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId) throws SQLException;
}