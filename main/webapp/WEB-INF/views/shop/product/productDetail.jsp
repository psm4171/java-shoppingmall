<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Detail</title>
    <style>
        /* Add your CSS styles here */
        /* For example, you can style the product image */
        .product-image {
            width: 200px;
            height: 200px;
        }

        /* Customize styles as needed */
    </style>
</head>
<body>
<h1>Product Detail</h1>

<c:if test="${empty product}">
    <p>Product not found.</p>
</c:if>

<c:if test="${not empty product}">
    <div>
        <h2>${product.modelName}</h2>
        <img src="/resources/image/${product.productImg}" alt="Product Image" class="product-image">
        <p>Category: ${product.categoryId}</p>
        <p>Model Number: ${product.modelNumber}</p>
        <p>Unit Cost: ${product.uniCost}</p>
        <p>Description: ${product.description}</p>
    </div>
</c:if>
</body>
</html>
