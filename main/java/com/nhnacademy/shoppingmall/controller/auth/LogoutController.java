package com.nhnacademy.shoppingmall.controller.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    //todo#13-3 로그아웃 구현

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // super.doGet(req, resp);

        HttpSession session = req.getSession(false);

        if(session != null){
            session.removeAttribute("userId");
            session.invalidate();
        }

        req.setAttribute("logoutMessage", "You have been successfully logged out.");
        resp.sendRedirect("/login.form");
    }
}
