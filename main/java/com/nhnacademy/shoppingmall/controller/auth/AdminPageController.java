package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;

import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/myadminAction.do")
public class AdminPageController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

//        String userId = String.valueOf(req.getSession().getAttribute("user_id"));
//        log.info("userId info: {}", userId);
//
//        User user = userService.getUser(userId);
//        log.info("user info: {}", user);
//
//        req.setAttribute("user", user);
        return "/admin/shop/mypage/myadmin";

    }
}
