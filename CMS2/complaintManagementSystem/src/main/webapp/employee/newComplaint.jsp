<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20/06/2025
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Complaint</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body class="container mt-5">
<h3>Create New Complaint</h3>
<form action="${pageContext.request.contextPath}/NewComplaintServlet" method="post">
    <div class="mb-3">
        <label for="title" class="form-label">Title</label>
        <input type="text" class="form-control" name="title" id="title" required>
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea class="form-control" name="description" id="description" required></textarea>
    </div>
    <button type="submit" class="btn btn-success">Submit Complaint</button>
</form>
</body>
</html>

