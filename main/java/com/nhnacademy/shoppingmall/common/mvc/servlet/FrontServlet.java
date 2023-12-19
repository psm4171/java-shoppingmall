package com.nhnacademy.shoppingmall.common.mvc.servlet;

import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.controller.ControllerFactory;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(
        name = "frontServlet",
        urlPatterns = {"*.do"}
)
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 100, // 100 MB
        //location 위치는 적절히 변경합니다.
        location = "/Users/parkseungmin/Documents/java-servlet-jsp-shoppingmall/src/main/resources/image"
)
public class FrontServlet extends HttpServlet {
    private ControllerFactory controllerFactory;
    private ViewResolver viewResolver;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        controllerFactory = (ControllerFactory) servletContext.getAttribute(ControllerFactory.CONTEXT_CONTROLLER_FACTORY_NAME);
        viewResolver = new ViewResolver();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ConnectException, ServletException {
        try {
            //todo#7-3 Connection pool로 부터 connection 할당 받습니다. connection은 Thread 내에서 공유됩니다.
            DbConnectionThreadLocal.initialize();

            BaseController baseController = (BaseController) controllerFactory.getController(req);
            String viewName = baseController.execute(req, resp);

            if (viewResolver.isRedirect(viewName)) {
                String redirectUrl = viewResolver.getRedirectUrl(viewName);
                log.debug("redirectUrl:{}", redirectUrl);
                //todo#7-6 redirect: 로 시작하면  해당 url로 redirect 합니다.
                if (redirectUrl.startsWith("redirect:")) {
                    resp.sendRedirect(redirectUrl.substring("redirect:".length()));

                }
            } else {
                String layout = viewResolver.getLayOut(viewName);
                String viewNameWithout = viewResolver.removeAdminUrl(viewName);
                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewNameWithout));

                log.warn("layout: {}", layout);
                log.warn("viewName: {}", viewName);
                RequestDispatcher rd = req.getRequestDispatcher(layout);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            log.error("error:{}", e);
            DbConnectionThreadLocal.setSqlError(true);
            //todo#7-5 예외가 발생하면 해당 예외에 대해서 적절한 처리를 합니다.
            throw new ServletException(e);

        } finally {
            //todo#7-4 connection을 반납합니다.
            try {
                DbConnectionThreadLocal.reset();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ConnectException, ServletException {
//        try {
//            //todo#7-3 Connection pool로 부터 connection 할당 받습니다. connection은 Thread 내에서 공유됩니다.
//            DbConnectionThreadLocal.initialize();
//
//            BaseController baseController = (BaseController) controllerFactory.getController(req);
//            String viewName = baseController.execute(req, resp);
//
//            if (viewResolver.isRedirect(viewName)) {
//                handleRedirect(req, resp, viewName);
//            } else {
//                String layout = viewResolver.getLayOut(viewName);
//                String viewNameWithout = viewResolver.removeAdminUrl(viewName);
//                req.setAttribute(ViewResolver.LAYOUT_CONTENT_HOLDER, viewResolver.getPath(viewNameWithout));
//
//                log.warn("layout: {}", layout);
//                log.warn("viewName: {}", viewName);
//                RequestDispatcher rd = req.getRequestDispatcher(layout);
//                rd.include(req, resp);
//            }
//        } catch (Exception e) {
//            log.error("error:{}", e);
//            DbConnectionThreadLocal.setSqlError(true);
//            //todo#7-5 예외가 발생하면 해당 예외에 대해서 적절한 처리를 합니다.
//            throw new ServletException(e);
//        } finally {
//            //todo#7-4 connection을 반납합니다.
//            try {
//                DbConnectionThreadLocal.reset();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//    private void handleRedirect(HttpServletRequest req, HttpServletResponse resp, String redirectViewName) throws IOException, SQLException {
//        Connection connection = null;
//        try {
//            connection = DbConnectionThreadLocal.getConnection();
//            String redirectUrl = viewResolver.getRedirectUrl(redirectViewName);
//            log.debug("redirectUrl:{}", redirectUrl);
//            if (redirectUrl.startsWith("redirect")) {
//                resp.sendRedirect(redirectUrl.substring("redirect:".length()));
//            }
//        } finally {
//            // Connection을 반납합니다.
//            if (connection != null) {
//                DbConnectionThreadLocal.reset();
//            }
//        }
//    }

//}