package com.nhnacademy.shoppingmall.controller.index;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@RequestMapping(method = RequestMapping.Method.GET,value = {"/adminindex.do"})
public class AdminIndexController implements BaseController {

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    @Override

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        List<Product> productList = productService.getAllProductsList();
        req.setAttribute("productList", productList);
        return "/admin/shop/main/adminindex";
    }
}
