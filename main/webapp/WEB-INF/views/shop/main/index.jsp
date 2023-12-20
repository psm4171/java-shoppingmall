<%@ page import="java.util.Objects" %>
<%@ page import="com.nhnacademy.shoppingmall.category.domain.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.nhnacademy.shoppingmall.product.domain.Product" %><%--&lt;%&ndash;--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

            <%
                List<Category> categoryList = (List<Category>) request.getAttribute("category");
                List<Product> productList = (List<Product>) request.getAttribute("productList");
            %>

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <form id="categoryForm"  method="post" action="/index.do" form="categoryForm">
                    <div class="form-floating">
                        <select class="form-select" id="categoryId" name="categoryId" required>
                            <option selected value="">카테고리 선택</option>
                            <%
                                if(Objects.nonNull(categoryList)){
                                    for(Category category : categoryList){
                            %>
                            <option value="<%=category.getCateogryId()%>"> <%=category.getCategryName()%></option>
                            <%
                                    }}
                            %>
                        </select>

                        <label for="categoryId">카테고리 ID</label>
                    </div>
                    <input type="submit" value="submit">
                </form>

                <% for(Product products : productList) { %>
                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top src="<%= products.getProductImg()%>" onerror="this.src='resources/no-image.png';" alt="resources/no-image.png" width = "100%" height = "200">
                        <div class="card-body">
                            <h5 class="card-title text-primary">상품 이름 : <%=products.getModelName()%></h5>
                            <p class="card-text"><small class="text-muted">상품 번호 : </small><%= products.getProductId()%></p>
                            <p class="card-text"><small class="text-muted">상품 가격 : </small><%= products.getUniCost()%></p>
                            <p class="card-text"><small class="text-muted">상품 설명 : </small><%= products.getDescription()%></p>

                            <div class="d-flex justify-content-between align-items-center">
<%--                                <div class="btn-group">--%>
<%--                                    <button type="button" onclick="location.href='/addShoppingCart.do?productId=<%= products.getProductId() %>'" class="btn btn-sm btn-outline-secondary">장바구니에 추가</button>--%>
<%--                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>



            </div>

            <img class="card-img-top src=">
            <div class="card-body">
                <h5 class="card-title text-primary">상품 이름 : </h5>

            </div>
        </div>
    </div>



</div>
