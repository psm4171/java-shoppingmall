package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    @Override
    public void execute() throws SQLException {

        DbConnectionThreadLocal.initialize();

        try {
            Connection connection = DbConnectionThreadLocal.getConnection();

            String sql = "Update users Set points = points + ? WHERE user_id = ?";
            try {


                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, String.valueOf(100));
                preparedStatement.setString(2, "user_id");

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                log.error("포인트 적립 실패: " + e.getMessage());
            }

            //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.
            log.debug("pointChannel execute");
        }finally {
            DbConnectionThreadLocal.reset();
        }
    }
}
