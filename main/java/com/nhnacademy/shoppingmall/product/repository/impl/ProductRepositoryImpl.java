package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    private static final String SAVE_SQL = "INSERT INTO Products (ProductID, CategoryID, ModelNumber, ModelName, ProductImage, UnitCost, Description) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Products";
    private static final String PRODUCT_BY_ID_SQL = "SELECT * FROM Products WHERE ProductID = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE Products SET CategoryID = ?, ModelNumber = ?, ModelName = ?, ProductImage = ?, UnitCost = ?, Description = ? WHERE ProductID = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM Products WHERE ProductID = ?";
    private static final String COUNT_PROUDCT_SQL = "SELECT COUNT(*) from Products WHERE ProductID = ?";

    @Override
    public Optional<Product> findByProductId(int productId) {

        log.debug("findByProductId:{}", productId);

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PRODUCT_BY_ID_SQL);
            preparedStatement.setInt(1, productId);

            try {
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("ProductID"),
                            rs.getInt("CategoryID"),
                            rs.getString("ModelNumber"),
                            rs.getString("ModelName"),
                            rs.getString("ProductImage"),
                            rs.getBigDecimal("Unitcost"),
                            rs.getString("Description")
                    );
                    return Optional.of(product);
                }

                if (productId == 0) {
                    throw new Exception("상품이 존재하지 않습니다.");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int saveProduct(Product product) {
        try  {
            Connection conn = DbConnectionThreadLocal.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SAVE_SQL);

            log.debug("ProductSave:{}", SAVE_SQL);

            pstmt.setInt(1, product.getProductId());
            pstmt.setInt(2, product.getCategoryId());
            pstmt.setString(3, product.getModelNumber());
            pstmt.setString(4, product.getModelName());
            pstmt.setString(5, product.getProductImg());
            pstmt.setBigDecimal(6, product.getUniCost());
            pstmt.setString(7, product.getDescription());
            int result = pstmt.executeUpdate();
            return result;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAllProductsList() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DbConnectionThreadLocal.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_SQL);

             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getString("ModelNumber"),
                        rs.getString("ModelName"),
                        rs.getString("ProductImage"),
                        rs.getBigDecimal("UnitCost"),
                        rs.getString("Description")
                );
                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


    @Override
    public int updateProduct(Product product) {
        try {
            Connection conn = DbConnectionThreadLocal.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT_SQL);
            pstmt.setInt(1, product.getProductId());
            pstmt.setInt(2, product.getCategoryId());
            pstmt.setString(3, product.getModelNumber());
            pstmt.setString(4, product.getModelName());
            pstmt.setString(5, product.getProductImg());
            pstmt.setBigDecimal(6, product.getUniCost());
            pstmt.setString(7, product.getDescription());
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int deleteByProductId(int productId) throws SQLException {

        try {
            Connection conn = DbConnectionThreadLocal.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCT_SQL);
            pstmt.setInt(1, productId);
            int result = pstmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public int countByProductId(int productId) {

        try {
            Connection conn = DbConnectionThreadLocal.getConnection();
            PreparedStatement psmt = conn.prepareStatement(COUNT_PROUDCT_SQL);

            psmt.setInt(1, productId);

            try {
                ResultSet rs = psmt.executeQuery();

                while(rs.next()){
                    return rs.getInt(1);
                }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}