<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/06/2025
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Complaints</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 80%; margin: 20px auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .message { color: green; margin-bottom: 15px; }
        .error { color: red; margin-bottom: 15px;}
    </style>
</head>
<body>
<div class="container">
    <h2>My Complaints</h2>

    <c:if test="${not empty sessionScope.message}">
        <p class="message">${sessionScope.message}</p>
        <c:remove var="message" scope="session" />
    </c:if>

    <c:if test="${not empty sessionScope.error}">
        <p class="error">${sessionScope.error}</p>
        <c:remove var="error" scope="session" />
    </c:if>

    <a href="newComplaint.jsp">Add New Complaint</a>

    <table>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${complaints}" var="complaint">
            <tr>
                <td>${complaint.title}</td>
                <td>${complaint.description}</td>
                <td>${complaint.status}</td>
                <td>${complaint.createdAt}</td>
                <td>
                    <a href="#" class="btn btn-warning">Edit</a>
                    <a href="#" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
