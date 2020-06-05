<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Products</title>
  </head>
  <body>
      <% if (session.getAttribute("user") == null) {
       response.sendRedirect("/login.jsp?failureMsg=You must login first");
       } else { %>
        <h2>${user.username}</h2>
        <a href="/logout.jsp">Logout</a> <br>
        <jsp:useBean id="products" class="com.example.model.ProductBean" />
            <c:forEach items="${products.allProducts}" var="product" >
                <c:out value="${product.id}" />
                <c:out value="${product.name}" />
                <c:out value="${product.description}" /> <br>
            </c:forEach>
      <a href="/addProduct.jsp">Add Product</a>
      <% } %>
  </body>
</html>
