<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <% if (request.getParameter("failureMsg") != null) { %>
        <p style="color: red;"><%=request.getParameter("failureMsg")%></p>
    <% } %>
    <form action="/user/login" method="post">
        Username: <input type="text" name="username"> <br>
        Password: <input type="password" name="password"> <br>
        <button>Login</button>
    </form>
    <br>
    <a href="register.jsp">Don't have an account yet ?</a>
</body>
</html>
