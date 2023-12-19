package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/signup.do")
public class SignupController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession(false);

//        try{
//            if (session != null && session.getAttribute("user_id") != null && session.getAttribute("user_password") != null && session.getAttribute("user_name") != null && session.getAttribute("user_birth") != null) {
//                resp.sendRedirect("/index.do");
//                session.invalidate();
//                return "redirect:/index.do";
//            } else {
//                return "shop/signup/signup";
//            }
//        }catch (Exception e){
//
//        }

        if (session != null && session.getAttribute("user_id") != null && session.getAttribute("user_password") != null && session.getAttribute("user_name") != null && session.getAttribute("user_birth") != null) {
            // 회원가입이 이미 진행된 경우 처리할 로직 추가
            resp.sendRedirect("/index.do");
            session.invalidate();
            return "redirect:/index.do";
        } else {
            return "shop/signup/signup";
        }


    }
}
