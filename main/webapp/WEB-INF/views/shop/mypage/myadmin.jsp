<%@ page contentType="text/ghtml;charset=UTF-8" language="java" session="true" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<main>
    <div class="container mt-5">
        <h1 class="mb-4">Admin MyPage</h1>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Admin Information</h5>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><strong>Admin ID:</strong> ${user_id.getUserId()}</li>
                    <li class="list-group-item"><strong>Admin Name:</strong> ${user_id.getUserName()}</li>
                    <li class="list-group-item"><strong>Admin Birth:</strong> ${user_id.getUserBirth()}</li>
                    <li class="list-group-item"><strong>Created At:</strong> ${user_id.getCreatedAt()}</li>
                    <li class="list-group-item"><strong>Latest Login At:</strong> ${user_id.getLatestLoginAt()}</li>
                </ul>
            </div>
        </div>
    </div>
</main>

