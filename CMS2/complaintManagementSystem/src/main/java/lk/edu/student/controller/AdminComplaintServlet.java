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

@WebServlet("/admin/complaints")
public class AdminComplaintServlet extends HttpServlet {

    private final ComplaintDAO complaintDAO = new ComplaintDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("../../login.jsp");
            return;
        }


        List<Complaint> complaints = complaintDAO.getAllComplaints();


        request.setAttribute("complaints", complaints);
        request.getRequestDispatcher("/admin/complaints.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");


        if (user == null || !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("../../login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("update".equals(action)) {

            int complaintId = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            String remarks = request.getParameter("remarks");

            Complaint complaint = new Complaint();
            complaint.setId(complaintId);
            complaint.setStatus(status);
            complaint.setRemarks(remarks);

            boolean success = complaintDAO.updateComplaint(complaint);

            if (success) {
                session.setAttribute("message", "Complaint updated successfully");
            } else {
                session.setAttribute("error", "Failed to update complaint");
            }

        } else if ("delete".equals(action)){

            int complaintId = Integer.parseInt(request.getParameter("id"));

            boolean success = complaintDAO.deleteComplaint(complaintId);

            if (success) {
                session.setAttribute("message", "Complaint deleted successfully");
            } else {
                session.setAttribute("error", "Failed to delete complaint");
            }
        }

        response.sendRedirect("complaints");
    }
}