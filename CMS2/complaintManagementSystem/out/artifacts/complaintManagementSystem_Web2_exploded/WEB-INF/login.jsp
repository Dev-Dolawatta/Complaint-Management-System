<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/06/2025
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Complaint Management System</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .container { width: 300px; margin: 100px auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; }
        input { width: 100%; padding: 8px; }
        button { padding: 8px 15px; background: #4CAF50; color: white; border: none; }
        .error { color: red; }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error">${error}</p>
    <% } %>
    <form action="login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
