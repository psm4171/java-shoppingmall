<%@ page import="java.util.List" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    .custom-card-img {
        width: 100%;
        height: auto;
    }
</style>

<div class="container">
    <h1>Product View & Product Edit Page</h1>
    <form method="post" action="/productAction.do">
        <div class="row">
            <c:set var="productList" value="${requestScope.productList}" />
            <c:forEach var="product" items="${productList}">
                <div class="col-md-4">
                    <div class="card shadow-sm custom-card-img">
                        <img src="/resources/image/${product.getProductImg()}" alt="Product Image" class="bd-placeholder-img card-img-top" style="width: 100%; height: auto;">
                        <div class="card-body">
                            <p class="card-text"><c:out value="${product.getDescription()}"/></p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-sm btn-outline-secondary" onclick="viewProduct('${product.getProductId()}', '${product.getCategoryId()}', '${product.getModelNumber()}', '${product.getModelName()}', '${product.getUniCost()}')">View</button>
                                    <form action="/productdeleteAction.do" method="POST">
                                        <input type="hidden" name="product_id" value="${product.getProductId()}">
<%--                                        <button type="submit">Delete</button>--%>
                                        <button type="button" class="btn btn-sm btn-outline-secondary" onclick="deleteProduct('${product.getProductId()}')">Delete</button>
                                    </form>
                                </div>
                                <small class="text-muted">Price: <c:out value="${product.getUniCost()}"/> 원</small>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
</div>
<script>
    function viewProduct(productId, categoryId, modelNumber, modelName, uniCost) {
        alert('상품 아이디: ' + productId +

            '\n카테고리: ' + categoryId +

            '\nModel Number: ' + modelNumber +

            '\nModel Name: ' + modelName +

            '\nUniCost: ' + uniCost
        );
        // Implement logic for viewing product details (e.g., display modal)
    }

    function deleteProduct(productId) {
        // Implement logic for editing product
        alert('상품 번호 ' + productId + '번을 삭제했습니다! 홈으로 이동시 적용됩니다.');
    }
</script>





<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <img src="/resources/image/2.jpg" alt="Product Image" class="bd-placeholder-img card-img-top">--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div class="col">--%>
<%--        <div class="card shadow-sm">--%>
<%--            <img src="/resources/image/3.jpg" alt="Product Image" class="bd-placeholder-img card-img-top">--%>

<%--            <div class="card-body">--%>
<%--                <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>--%>
<%--                <div class="d-flex justify-content-between align-items-center">--%>
<%--                    <div class="btn-group">--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">View</button>--%>
<%--                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>--%>
<%--                    </div>--%>
<%--                    <small class="text-muted">9 mins</small>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>


<%--    </div>--%>
<%--</div>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>

