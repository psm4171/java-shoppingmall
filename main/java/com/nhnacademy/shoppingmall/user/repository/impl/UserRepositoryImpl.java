package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.util.DbUtils;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import javax.management.relation.Role;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /* todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */


        String sql = "Select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users WHERE user_id = ? AND user_password = ?";

        log.debug("sql:{}", sql);

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userPassword);

            try {
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<User> findById(String userId) {
        // todo#3-2 회원조회

        String sql = "SELECT user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at FROM users WHERE user_id = ?";
        log.debug("findById:{}", userId);

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            try {
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    User user = new User(
                            rs.getString("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_password"),
                            rs.getString("user_birth"),
                            User.Auth.valueOf(rs.getString("user_auth")),
                            rs.getInt("user_point"),
                            Objects.nonNull(rs.getTimestamp("created_at")) ? rs.getTimestamp("created_at").toLocalDateTime() : null,
                            Objects.nonNull(rs.getTimestamp("latest_login_at")) ? rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                    );
                    return Optional.of(user);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.

        String sql = "INSERT INTO users (user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        log.debug("save:{}", sql);

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setString(4, user.getUserBirth());
            preparedStatement.setString(5, String.valueOf(user.getUserAuth()));
            preparedStatement.setString(6, String.valueOf(user.getUserPoint()));
            preparedStatement.setTimestamp(7, user.getCreatedAt() != null ? Timestamp.valueOf(user.getCreatedAt()) : null);
            preparedStatement.setTimestamp(8, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int deleteByUserId(String userId) {
        // todo#3-4 회원삭제, executeUpdate()을 반환합니다.

        String sql = "DELETE from users WHERE user_id = ?";

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);

            int result = preparedStatement.executeUpdate();
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        // todo#3-5 회원수정, executeUpdate()을 반환합니다.

        String sql = "UPDATE users SET user_id = ?, user_name = ?, user_password = ?, user_birth = ?, user_auth = ?, user_point = ?, created_at = ?, latest_login_at = ?";

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setString(4, user.getUserBirth());
            preparedStatement.setString(5, String.valueOf(user.getUserAuth()));
            preparedStatement.setString(6, String.valueOf(user.getUserPoint()));
            preparedStatement.setTimestamp(7, user.getCreatedAt() != null ? Timestamp.valueOf(user.getCreatedAt()) : null);
            preparedStatement.setTimestamp(8, user.getLatestLoginAt() != null ? Timestamp.valueOf(user.getLatestLoginAt()) : null);
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {

        String sql = "UPDATE users SET latest_login_at = ? where user_id = ?";
        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setTimestamp(1, Timestamp.valueOf(latestLoginAt));
            preparedStatement.setString(2, userId);

            int reuslt = preparedStatement.executeUpdate();
            return reuslt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.

        String sql = "SELECT COUNT(*) from users WHERE user_id = ?";

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, userId);

            try {

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
