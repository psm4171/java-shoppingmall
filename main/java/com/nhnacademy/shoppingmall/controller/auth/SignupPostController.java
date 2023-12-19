package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
public class SignupPostController implements BaseController {

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private int userPoint = 1000000;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");
        String userName = req.getParameter("user_name");
        String userBirth = req.getParameter("user_birth");
        User.Auth userAuth = User.Auth.ROLE_USER;
        LocalDateTime userCreatedAt = LocalDateTime.now();
        LocalDateTime userLatest_login = LocalDateTime.now();

        try {
            User newUser = new User(userId, userName, userPassword, userBirth, userAuth, userPoint, userCreatedAt, userLatest_login);
            log.info("new Use:{}", newUser.getUserId());
            if(newUser != null && newUser.getUserId() != null || newUser.getUserPassword() != null || newUser.getUserName() != null || newUser.getUserBirth() != null){
                userService.saveUser(newUser);
                log.info("new Use:{}", newUser.getUserId());
                HttpSession session = req.getSession();
                session.setAttribute("newuser", newUser);
                return "shop/main/index";
            } else {
                return "shop/signup/signup";
            }
        } catch (UserAlreadyExistsException e){
            req.setAttribute("errorMessage", e.getMessage());
            return "shop/signup/signup";
        }

    }
}
