package com.nhnacademy.shoppingmall.common.listener;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.net.ServerSocket;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class ApplicationListener implements ServletContextListener {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //todo#12 application 시작시 테스트 계정인 등admin,user 록합니다. 만약 존재하면 등록하지 않습니다.
        log.debug("ApplicationListener - init");
        try {
            DbConnectionThreadLocal.initialize();

            User successLoginAdmin = userService.getUser("admin");
            User successLoginUser = userService.getUser("user");
            if (Objects.isNull(successLoginAdmin)) {
               // log.debug("admin은 null입니다.");
                User administrator = new User("admin", "admin", "12345", "19980504", User.Auth.ROLE_ADMIN, 0, LocalDateTime.now(), LocalDateTime.now());
                userService.saveUser(administrator);
                log.info("Admin 계정 등록 : {}", administrator);
            } else {
                log.info("Admin 계정이 이미 존재합니다: {}", successLoginAdmin);
            }

            if (successLoginUser == null) {
                User user = new User("user", "user", "12345", "19991212", User.Auth.ROLE_USER, 1000000, LocalDateTime.now(), LocalDateTime.now());
                userService.saveUser(user);
                log.info("User 계정 등록 : {}", user);
            } else {
                log.info("User 계정이 이미 존재합니다: {}", successLoginUser);
            }

            DbConnectionThreadLocal.reset();
        } catch (SQLException e) {
            log.error("예외 발생: " + e);
            throw new RuntimeException(e);

        }

        ServletContext context = sce.getServletContext();
        context.setAttribute("userService", userService);
        context.setAttribute("productService", productService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
