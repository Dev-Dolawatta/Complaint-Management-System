package lk.edu.student.controller;

import jakarta.activation.DataSource;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.edu.student.model.Complaint;
import lk.edu.student.model.dao.ComplaintDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/NewComplaintServlet")
public class NewComplaintServlet extends HttpServlet {

        private ComplaintDAO complaintDAO;

        @Override
        public void init() {

        complaintDAO = new ComplaintDAO();
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("user_id") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            int userId = (int) session.getAttribute("user_id");

            Complaint complaint = new Complaint();
            complaint.setTitle(title);
            complaint.setDescription(description);
            complaint.setStatus("PENDING");
            complaint.setUserId(userId);

            try {
                complaintDAO.addComplaint(complaint);
                response.sendRedirect("employee/dashboard.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().write("saving complaint failed!.");
            }
        }


}
