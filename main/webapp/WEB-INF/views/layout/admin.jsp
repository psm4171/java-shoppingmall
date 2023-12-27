<%@ page import="java.util.Objects" %>
<%@ page import="com.nhnacademy.shoppingmall.user.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <title>nhn아카데미 쇼핑몰</title>

</head>
<body>
<form class="mainContainer">
    <%
        User user = (User) session.getAttribute("user");
    %>
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <form method="post" action="/signupAction.do">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

                    <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                            <use xlink:href="#bootstrap"></use>
                        </svg>
                    </a>

                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="/adminindex.do" class="nav-link px-2 text-secondary">홈</a></li>
                        <%
                            if(Objects.nonNull(user))
                        %>
                        <% {
                        %>
                        <li><a href="/myuser.do" class="nav-link px-2 text-white">마이페이지</a></li>
                        <% }
                        %>

                        <%
                            if(Objects.nonNull(User.Auth.ROLE_ADMIN))
                        %>
                        <% {
                        %>
                        <li><a href="/myadminAction.do" class="nav-link px-2 text-white">관리자 페이지</a></li>

                        <li><a href="/productEnroll.do" class="nav-link px-3 text-white">상품 등록</a></li>
                        <% }
                        %>

<%--                        <li><a href="/productEdit.do" class="nav-link px-3 text-white">상품 조회 및 수정</a></li>--%>
                    </ul>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                        <input type="search" class="form-control form-control-dark" placeholder="검색..." aria-label="검색">
                    </form>

                    <div class="text-end">
                        <% if (session.getAttribute("user_id") != null) { %>
                        <a class="btn btn-outline-light me-2" href="/login.do">로그아웃</a>
                        <% } else { %>
                        <a class="btn btn-outline-light me-2" href="/login.do">로그인</a>
                        <a class="btn btn-warning" href="/signup.do">회원가입</a>
                        <% } %>
                    </div>
                </div>
            </form>
        </div>
    </header>

    <main>
        <div class="album py-5 bg-light">
            <div class="container">
                <jsp:include page="${layout_content_holder}"/>
            </div>
        </div>

    </main>

    <footer class="text-muted py-5">
        <div class="container">
            <p class="float-end mb-1">
                <a href="#">맨 위로 이동</a>
            </p>
            <p class="mb-1">쇼핑몰 예제는 © nhnacademy.com에 속합니다.</p>
        </div>
    </footer>
    </div>
</form>
</body>
</html>
