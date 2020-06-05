<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>Register</h2>
    <% if (request.getParameter("failureMsg") != null) { %>
        <p style="color: red;"><%=request.getParameter("failureMsg")%></p>
    <% } %>
    <form action="/user/register" method="post">
        Username: <input type="text" name="username"> <br>
        Password: <input type="password" name="password"> <br>
        Repeat Password: <input type="password" name="repeatPassword"> <br>
        <button>Submit</button>
    </form>
    <br>
    <a href="/login.jsp">Already have an account ?</a>
</body>
</html>
