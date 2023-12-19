package com.nhnacademy.shoppingmall.user.service;

import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;

public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

    boolean isAuthenticated(HttpSession session);

    User doSignup(String userId, String userPassword, String userName, String userBirth, User.Auth userAuth, int userPoint, LocalDateTime userCreatedAt, LocalDateTime userLatest_login) throws UserAlreadyExistsException;
}
