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
    <title>Edit a Name</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
    <!-- change to match your file/naming structure -->
  </head>
  <body>
    ${thename}
    <div class="container">
      <h1>Edit Name</h1>
      <a href="/logout">Logout</a>

      <form:form action="/edit/${id}" method="post" modelAttribute="thename">
        <form:hidden path="user" value="${userId}"></form:hidden>
        <input type="hidden" name="_method" value="put">
        <p>
          <form:label path="name">Name</form:label>
          <form:errors path="name" />
          <form:input path="name" value="${name.name}" class="form-control" />
        </p>
        <p>
          <form:label path="gender" class="form-label">Typical Gender:</form:label>
          <form:errors path="gender" />
          <form:select path="gender" class="form-select" value="${name.gender}">
              <form:option value="Neutral" />
              <form:option value="Female" />
              <form:option value="Male" />
          </form:select>
        </p>
        <p>
          <form:label path="origin">Origin</form:label>
          <form:errors path="origin" />
          <form:input
            path="origin"
            value="${name.origin}"
            class="form-control"
          />
        </p>
        <p>
          <form:label path="meaning">Meaning</form:label>
          <form:errors path="meaning" />
          <form:input
            path="meaning"
            value="${name.meaning}"
            class="form-control"
          />
        </p>
        <input type="submit" value="submit" class="btn btn-secondary" />
      </form:form>

      <a href="/home">Cancel</a>

      <c:if test="${name.getUser().getId() == user.getId()}">
        <div>
          <form action="/names/delete/${thename.id}" method="post">
            <input type="hidden" name="_method" value="delete">
            <input type="submit" value="Delete">    
          </input>  
        </div>
      </c:if>

    </div>
  </body>
</html>
