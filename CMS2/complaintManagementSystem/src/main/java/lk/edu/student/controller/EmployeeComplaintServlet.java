package lk.edu.student.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.edu.student.model.dao.ComplaintDAO;
import lk.edu.student.model.Complaint;
import lk.edu.student.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/employee/complaints")
public class EmployeeComplaintServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"EMPLOYEE".equals(user.getRole())) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        ComplaintDAO complaintDAO = new ComplaintDAO();
        List<Complaint> complaints = complaintDAO.getComplaintsByUser(user.getId());

        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("/employee/complaints.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"EMPLOYEE".equals(user.getRole())) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Complaint complaint = new Complaint();
        complaint.setTitle(title);
        complaint.setDescription(description);
        complaint.setUserId(user.getId());

        ComplaintDAO complaintDAO = new ComplaintDAO();
        boolean success = complaintDAO.addComplaint(complaint);

        if (success) {
            session.setAttribute("message", "Complaint added successfully");
        } else {
            session.setAttribute("error", "Failed to add complaint");
        }

        response.sendRedirect("complaints");
    }
}
