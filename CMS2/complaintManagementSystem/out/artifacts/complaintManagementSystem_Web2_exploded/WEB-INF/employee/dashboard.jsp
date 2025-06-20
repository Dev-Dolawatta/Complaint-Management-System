<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 14/06/2025
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard - CMS</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 1px solid #eee;
        }
        .welcome {
            color: #2c3e50;
            margin: 0;
        }
        .nav-links a {
            margin-left: 15px;
            text-decoration: none;
            color: #3498db;
            font-weight: 500;
        }
        .stats {
            display: flex;
            gap: 20px;
            margin-bottom: 30px;
        }
        .stat-card {
            flex: 1;
            padding: 15px;
            background: #f8f9fa;
            border-radius: 5px;
            text-align: center;
        }
        .stat-card h3 {
            margin-top: 0;
            color: #7f8c8d;
        }
        .stat-card .count {
            font-size: 24px;
            font-weight: bold;
            color: #2c3e50;
        }
        .quick-actions {
            margin-bottom: 30px;
        }
        .btn {
            display: inline-block;
            padding: 8px 15px;
            background: #3498db;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            margin-right: 10px;
        }
        .btn:hover {
            background: #2980b9;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <h1 class="welcome">Welcome, ${user.fullName}!</h1>
        <div class="nav-links">
            <a href="complaints">My Complaints</a>
            <a href="../logout">Logout</a>
        </div>
    </div>

    <div class="stats">
        <div class="stat-card">
            <h3>Pending Complaints</h3>
            <div class="count">${pendingCount}</div>
        </div>
        <div class="stat-card">
            <h3>In Progress</h3>
            <div class="count">${inProgressCount}</div>
        </div>
        <div class="stat-card">
            <h3>Resolved</h3>
            <div class="count">${resolvedCount}</div>
        </div>
    </div>

    <div class="quick-actions">
        <a href="add-complaint.jsp" class="btn">+ New Complaint</a>
        <a href="complaints?filter=pending" class="btn">View Pending</a>
        <a href="complaints?filter=recent" class="btn">Recent Activity</a>
    </div>

    <h2>Recent Complaints</h2>
    <c:choose>
        <c:when test="${not empty recentComplaints}">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Status</th>
                    <th>Last Updated</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${recentComplaints}" var="complaint">
                    <tr>
                        <td>${complaint.id}</td>
                        <td><a href="complaints?id=${complaint.id}">${complaint.title}</a></td>
                        <td>
                                    <span class="status-${complaint.status.toLowerCase()}">
                                            ${complaint.status}
                                    </span>
                        </td>
                        <td>
                            <fmt:formatDate value="${complaint.updatedAt}" pattern="MMM dd, yyyy HH:mm"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>No complaints found. <a href="add-complaint.jsp">Create your first complaint</a></p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
