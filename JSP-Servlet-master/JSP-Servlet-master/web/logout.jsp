<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
    <%
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
            response.sendRedirect("/login.jsp");
        } else {
            response.sendRedirect("/login.jsp");
        }
    %>
</body>
</html>
