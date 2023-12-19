<%--
  Created by IntelliJ IDEA.
  User: parkseungmin
  Date: 12/12/23
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=divice-width, initial-scale=1.0">
    <title>로그인 페이지</title>
</head>

<body>
<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form id="loginForm" method="post" action="/loginAction.do" form="loginForm">
            <h1 class="h3 mb-3 fw-normal">Please Sign in</h1>

            <div class="form-floating">
                <input type="user_id" name="user_id" class="form-control" id="user_id" placeholder="ID">
            </div>
            <div class="form-floating">
                <input type="user_password" name="user_password" class="form-control" id="user_password"
                       placeholder="Password">
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit" onClick="location.href='/login.do'">Sign
                in
            </button>
        </form>
    </div>

    <script>
        function submitForm() {
            let id = document.getElementById("user_id").value;
            let pw = document.getElementById("user_password").value;

            if (id.trim() === '') {
                document.getElementById('errorMessage').innerText = "Id is required.";
                return;
            } else if (pw.trim() === '') {
                document.getElementById('errorMessage').innerText = "Id is required.";
                return;
            }

            document.getElementById('errorMessage').innerText = '';

            document.getElementById('loginForm').submit();
        }

        // window.addEventListener("DOMContentLoaded", function (){
        //     const loginForm= document.getElementById("loginForm");
        //
        //     loginForm.addEventListener("submit", function (event){
        //         const userId= document.getElementById("user_id");
        //         const userPw= document.getElementById("user_password");
        //         if(userId.value.length === 0){
        //             alert("아이디 다시 입력");
        //             userId.focus();
        //             event.preventDefault();
        //         }else if(userPw.value.length === 0){
        //             alert("비밀번호 다시 입력");
        //             userPw.focus();
        //             event.preventDefault();
        //         }
        //     });
        // });

    </script>
</div>
</body>
</html>


