<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Complaint Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .status-pending { color: orange; }
        .status-in_progress { color: blue; }
        .status-resolved { color: green; }
        .form-inline { display: inline; }
        .message { color: green; margin: 10px 0; }
        .error { color: red; margin: 10px 0; }
    </style>
</head>
<body>
<div class="container">
    <h1>Complaint Management</h1>
    <p>Welcome, Admin</p>

    <c:if test="${not empty sessionScope.message}">
        <p class="message">${sessionScope.message}</p>
        <c:remove var="message" scope="session" />
    </c:if>

    <c:if test="${not empty sessionScope.error}">
        <p class="error">${sessionScope.error}</p>
        <c:remove var="error" scope="session" />
    </c:if>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Remarks</th>
            <th>Created By</th>
            <th>Created At</th>
            <th>Updated At</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${complaints}" var="complaint">
            <tr>
                <td>${complaint.id}</td>
                <td>${complaint.title}</td>
                <td>${complaint.description}</td>
                <td class="status-${complaint.status.toLowerCase().replace(' ', '_')}">
                        ${complaint.status}
                </td>
                <td>${complaint.remarks}</td>
                <td>User ID: ${complaint.userId}</td>
                <td>${complaint.createdAt}</td>
                <td>${complaint.updatedAt}</td>
                <td>
                    <form class="form-inline" action="complaints" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${complaint.id}">
                        <select name="status">
                            <option value="PENDING" ${complaint.status == 'PENDING' ? 'selected' : ''}>Pending</option>
                            <option value="IN_PROGRESS" ${complaint.status == 'IN_PROGRESS' ? 'selected' : ''}>In Progress</option>

                        </select>
                        <input type="text" name="remarks" value="${complaint.remarks}" placeholder="Remarks">
                        <button type="submit" class="btn btn-warning">Update</button>
                    </form>

                    <form class="form-inline" action="complaints" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${complaint.id}">
                        <button type="submit" class="btn btn-danger">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="../login.jsp" class="btn btn-info">Logout</a>
</div>
</body>
</html>