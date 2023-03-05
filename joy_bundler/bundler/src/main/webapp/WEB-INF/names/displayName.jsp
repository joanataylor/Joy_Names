<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Display Name</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
    <!-- change to match your file/naming structure -->
  </head>
  <body>
    <div class="container">
      <a href="/logout">Logout</a>
      <a href="/home">Back to all names</a>
      <h1>${names.name}</h1>
      <h6>(added by ${names.user.userName})</h6>
    <p>Gender: ${names.gender}</p>
    <p>Origin: ${names.origin}</p>
    <p>Here is the meaning: ${names.meaning}</p>
    <c:if test="${userId == names.user.id}">
      <div>
      <a href="/names/${names.id}/edit">Edit</a>
    </div>
    </c:if>
    </div>
  </body>
</html>