package com.nhnacademy.shoppingmall.common.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/login/*"},
        initParams = {
                @WebInitParam(
                        name = "exclude-urls",
                        value = "/login_form.jsp")
        }
)
@Slf4j
public class LoginCheckFilter extends HttpFilter {

    private final Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        String urls = config.getInitParameter("exclude-urls");
        Arrays.stream(urls.split("\n"))
                .map(String::trim)
                .forEach(excludeUrls::add);
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.

        String requestUri = req.getRequestURI();
        if(!excludeUrls.contains(requestUri)) {
            HttpSession session = req.getSession(true);
            if(Objects.isNull(session)){
                res.sendRedirect("/login_form.jsp");
            }
        }

        chain.doFilter(req, res);

    }
}