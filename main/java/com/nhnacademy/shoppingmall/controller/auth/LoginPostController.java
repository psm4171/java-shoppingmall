package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.filter.AdminCheckFilter;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

//@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
//public class LoginPostController implements BaseController {
//    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());
//
//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
//
//        String userId = req.getParameter("user_id");
//        String userPassword = req.getParameter("user_password");
//
//        try {
//            User user = userService.doLogin(userId, userPassword);
//            HttpSession session = req.getSession();
//            session.setAttribute("user_id", user);
//            session.setMaxInactiveInterval(60 * 60);
//
//            if (userService.getUser(userId).getUserAuth().equals(User.Auth.ROLE_ADMIN)) {
//                return "/admin/shop/main/adminindex";
//            }
//            //return "shop/main/index.do";
//            return "redirect:/index.do";
//        }catch (UserNotFoundException e) {
//            req.setAttribute("errorMessage","로그인에 실패했습니다! 다시 입력해주세요.");
//            return "redirect:/login_form";
//        }
//    }
//}

@Slf4j

@RequestMapping(method = RequestMapping.Method.POST,value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        log.info("userId:{} ",userId);
        String userPassword=req.getParameter("user_password");
        log.info("user_password:{}",userPassword);
        try {
            userService.doLogin(userId,userPassword);
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(60*60);
            session.setAttribute("user_id", userId);

        }catch (UserNotFoundException e){
            log.info("로그인에 실패했어요");
            req.setAttribute("error_message", "로그인에 실패했습니다.");
            return "/shop/login/login_form";
        }
        if(userService.getUser(userId).getUserAuth().equals(User.Auth.ROLE_ADMIN)){
            log.info("userAuth:{}", userService.getUser(userId));
            return "redirect:/adminIndex.do";

        }else {

            return "redirect:/index.do";
        }
    }
}
