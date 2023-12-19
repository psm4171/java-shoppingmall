<%@ page import="java.util.Objects" %>
<%@ page import="com.nhnacademy.shoppingmall.category.domain.Category" %>
<%@ page import="java.util.List" %><%--&lt;%&ndash;--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

            <%
                List<Category> categoryList = (List<Category>) request.getAttribute("category");
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

                <div class="col">
                    <div class="card shadow-sm">
                        <img class="card-img-top src=">
                        <div class="card-body">
                            <h5 class="card-title text-primary">상품 이름 : </h5>

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
