package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/productdeleteAction.do")
public class ProductDeleteController implements BaseController {

    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        int productId =Integer.parseInt(req.getParameter("ProductID"));
        productService.deleteProduct(productId);
        log.info("productDelete:{}", productId);

        return "/admin/shop/main/adminindex";
    }
}

