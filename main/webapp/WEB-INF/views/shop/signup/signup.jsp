<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" session="false" %>

<div style="margin: auto; width: 400px;">
    <div class="p-2">
        <form method="post" action="/signupAction.do">

            <h1 class="h3 mb-3 fw-normal">Please Sign up</h1>

            <div class="form-floating">
                <input type="text" name="user_id" class="form-control" id="user_id" required>
                <label for="user_id">ID</label>
            </div>

            <div class="form-floating">
                <input type="password" name="user_password" class="form-control" id="user_password" required>
                <label for="user_password">Password</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_name" class="form-control" id="user_name" required>
                <label for="user_name">Name</label>
            </div>

            <div class="form-floating">
                <input type="text" name="user_birth" class="form-control" id="user_birth"  required>
                <label for="user_birth">Birth ex.19980504</label>
            </div>

            <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign up</button>

            <p class="mt-5 mb-3 text-muted">© 2022-2024</p>

        </form>
    </div>
</div>