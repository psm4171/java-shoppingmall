<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/productAction.do" enctype="multipart/form-data">

            <h1 class="h3 mb-3 fw-normal">Enroll Product Page</h1>

            <div class="form-floating">
                <input type="text" name="ProductID" class="form-control" id="ProductID" placeholder="상품 아이디" required>
                <label for="ProductID">Product ID</label>
            </div>

            <div class="form-floating">
                <input type="text" name="CategoryID" class="form-control" id="CategoryID" placeholder="카테고리 아이디" required>
                <label for="CategoryID">Category ID</label>
            </div>

            <div class="form-floating">
                <input type="text" name="ModelNumber" class="form-control" id="ModelNumber" placeholder="모델 번호" required>
                <label for="ModelNumber">Model Numer</label>
            </div>

            <div class="form-floating">
                <input type="text" name="ModelName" class="form-control" id="ModelName" placeholder="모델 이름" required>
                <label for="ModelName">Model Name</label>
            </div>

            <div class="form-floating">
                <input type="file" name="productImage" class="form-control" id="ProductImage" required>
                <label for="ProductImage">Product Image</label>
            </div>
<%--            <script>--%>
<%--                const fileInput = document.getElementById('ProductImage');--%>

<%--                fileInput.addEventListener('change', function ()){--%>
<%--                    const selecedFiles = fileInput.files;--%>

<%--                    for(let i =0; i < selecedFiles.length; i++){--%>
<%--                        const file = selecedFiles[i];--%>
<%--                        const fileName = file.name;--%>
<%--                    }--%>
<%--                }--%>
<%--            </script>--%>

            <div class="form-floating">
                <input type="text" name="UnitCost" class="form-control" id="UnitCost" placeholder="상품 가격" required>
                <label for="UnitCost">Product Price</label>
            </div>

            <div class="form-floating">
                <input type="text" name="Description" class="form-control" id="Description" placeholder="상품 특징" required>
                <label for="Description">Product Description</label>
            </div>

<%--            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit" href="/productEnroll.do">상품 등록</button>--%>
            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit" href="/adminindex.do">상품 등록</button>
            <div id="confirmationMessage" style="display: none;">등록이 완료되었습니다.</div>

            <script>
                function showEnrollSuccessMessage(){
                    var comfirmationMessage = document.getElementById("confirmationMessage");
                    comfirmationMessage.style.display = "block";
                }
            </script>
            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>


        </form>
    </div>
</div>