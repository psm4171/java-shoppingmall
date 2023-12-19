package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/productEnroll.do")

public class ProductController implements BaseController {


    private ProductService productService;
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("product enroll: ");

//        User.Auth userRole = (User.Auth) req.getSession().getAttribute("userRole");
//
//        List<Product> productList;
//        if(User.Auth.ROLE_ADMIN.equals(userRole)){
//            productList = productService.getAllProductsList();
//        }else {
//            productList = productService.getProductsForRole(User.Auth.ROLE_USER);
//        }
//
//        req.setAttribute("productList", productList);


      //  ProductService productService = (ProductService) req.getServletContext().getAttribute("productService");

        return "/admin/layout/productEnroll";
    }
}