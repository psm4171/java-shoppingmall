<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/loginAction.do">

            <h1 class="h3 mb-3 fw-normal">Please Sign in</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" required>
                <%--                <label for="user_id">회원아이디</label>--%>
                <label for="user_id">ID</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" required>
                <%--                <label for="user_password">비밀번호</label>--%>
                <label for="user_password">Password</label>
            </div>

            <%
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
            <%
                }
            %>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit" onClick="location.href='/login.do'">Sign in</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>
