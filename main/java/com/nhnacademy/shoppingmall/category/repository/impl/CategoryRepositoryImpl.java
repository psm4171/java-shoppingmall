package com.nhnacademy.shoppingmall.category.repository.impl;

import com.nhnacademy.shoppingmall.category.domain.Category;
import com.nhnacademy.shoppingmall.category.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {


    @Override
    public Optional<Category> findByCategorytId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select *from Categories where CategoryID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, categoryId);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return Optional.of(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> findByCategoryName(String categoryName) throws SQLException {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select *from Categories where CategoryNmae = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, categoryName);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return Optional.of(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into Categories(CatetoryName) values(?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getCategryName());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteByCategoryId(int categoryId) throws SQLException {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Categories where CategoryID = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, categoryId);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByCategoryName(String categoryName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from Categories where CategoryName = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, categoryName);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Categories set CategoryName = ? where CategoryID = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, category.getCategryName());
            preparedStatement.setInt(2, category.getCateogryId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateByCategoryId(Category category) throws SQLException {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update Categories set CategoryName = ? where CategoryID = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, category.getCategryName());
            preparedStatement.setInt(2, category.getCateogryId());
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }


    @Override
    public int countByCategoryName(String categoryName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from Categories where CategoryName = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, categoryName);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                return rs.getInt(1);
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return 0;
    }

    @Override
    public List<Category> findAll() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select *from Categories";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            List<Category> categoryList = new ArrayList<>();
            while(rs.next()){
                categoryList.add(new Category(rs.getInt("CategoryID"), rs.getString("CategoryName")));
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
