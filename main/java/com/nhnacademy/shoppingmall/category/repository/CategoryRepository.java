package com.nhnacademy.shoppingmall.category.repository;

import com.nhnacademy.shoppingmall.category.domain.Category;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findByCategorytId(int categoryId);
    Optional<Category> findByCategoryName(String categoryName) throws SQLException;
    int save(Category category);
    int deleteByCategoryId(int categoryId) throws SQLException;
    int deleteByCategoryName(String categoryName);
    int update(Category category);
    int updateByCategoryId(Category category) throws SQLException;
    int countByCategoryName(String categoryName);

    List<Category> findAll();

}
