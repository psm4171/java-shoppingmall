package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.mvc.view.ViewResolver;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/productAction.do")
public class ProductPostController implements BaseController {

    private ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String UPLOAD_DIR = "/resources/image";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, SQLException {

        log.error("Connection is null at this point. Check the preceding code for issues.");
        Part productImage = req.getPart("productImage");
        int productId = Integer.parseInt(req.getParameter("ProductID"));
        int categoryId = Integer.parseInt(req.getParameter("CategoryID"));
        String modelNumber = req.getParameter("ModelNumber");
        String modelName = req.getParameter("ModelName");
        String contentDisposition = productImage.getHeader(CONTENT_DISPOSITION);
        BigDecimal unitCost = new BigDecimal(req.getParameter("UnitCost"));
        String dscription = req.getParameter("Description");

        log.info("이미지 확인{}", productImage);

        String fileName = null;

        if (contentDisposition.contains("filename=")) {
            fileName = extractFileName(contentDisposition);
            if (productImage.getSize() > 0) {
                productImage.write(getUploadDir(req, fileName));
            }
        }

        String productImageStr = fileName;

        try {
            Product product = new Product(productId, categoryId, modelNumber, modelName, productImageStr, unitCost, dscription);
            log.info("new Product:{}", product.getProductId());

            productService.saveProduct(product);

            HttpSession session = req.getSession();
            session.setAttribute("newProduct", product);
            session.setMaxInactiveInterval(0);
            log.info("상품을 저장했습니다. ProductID: {}", productId);

            List<Product> productList = productService.getAllProductsList();
            req.setAttribute("productList", productList);
            log.info("productList 저장. productList: {}", productList);

            log.info("productImage 저장. productImage: {}", productImage);

            return "/admin/shop/main/adminindex";
            //return "redirect:/adminindex";
            //return "/productAction.do";
            //   return "/admin/shop/product/productView";

        } catch (Exception e) {
            log.error("상품 저장에 실패했습니다.", e);
            return "/admin/layout/productEnroll";
        }
    }

    private String extractFileName(String contentDisposition) {
        log.error("contentDisposition:{}", contentDisposition);
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }

    public static String getUploadDir(HttpServletRequest rq, String fileName) {

        String uploadPath = rq.getServletContext().getRealPath(UPLOAD_DIR); // 업로드 경로를 얻어옵니다.
        log.info("uploadPath :{}", uploadPath);
        String filePath = uploadPath + File.separator + fileName; // 파일 경로를 생성합니다.
        log.info("filePath :{}", filePath);
        return filePath;
    }


}

