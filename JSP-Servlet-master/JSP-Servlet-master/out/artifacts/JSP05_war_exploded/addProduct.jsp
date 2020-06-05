<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product</title>
</head>
<body>
    <% if (session.getAttribute("user") == null) {
        response.sendRedirect("/login.jsp?failureMsg=You must login first");
    } else { %>
    <h2>Add Product</h2>
    <form action="/product" method="post">
        Name: <input type="text" name="name">
        Description: <input type="text" name="description">
        <button>Submit</button>
    </form>
    <% } %>
</body>
</html>
