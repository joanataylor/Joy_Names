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
    <title>Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/css/main.css" />
    <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script>
    <!-- change to match your file/naming structure -->
  </head>
  <body>
    <div class="container" class="d-flex">
      <h1 style="color: blue">Hello, <c:out value="${user.userName}" />!</h1>
      <h2>Here are some name suggestions..</h2>
      <a href="/logout">Logout</a>
      
      
      
      <div class="container">
        <h3>Baby Names</h3>
        <table class="table table-bordered">
          <thead class="table-info">
            <tr>
              <!-- <th scope="col">Id</th> -->
              <th scope="col">Name</th>
              <th scope="col">Gender</th>
              <th scope="col">Origin</th>
              <!-- <th scope="col">Delete</th>
                <th scope="col">Edit</th> -->
              </tr>
            </thead>
            <tbody>
              <c:forEach var="n" items="${names}">
                <tr class="table-secondary">
                  <td scope="row"><a href="/names/${n.id}">${n.name}</a></td>
                  <td scope="row">${n.gender}</td>
                  <td scope="row">${n.origin}</td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
          <a href="/names/new">new name</a>
        </div>
        
      </div>
    </body>
    </html>
