package lk.edu.student.model.dao;

import lk.edu.student.model.Complaint;
import lk.edu.student.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints ORDER BY created_at DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                complaints.add(new Complaint(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("remarks"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at"),
                        rs.getInt("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }

    public List<Complaint> getComplaintsByUser(int userId) {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    complaints.add(new Complaint(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("status"),
                            rs.getString("remarks"),
                            rs.getTimestamp("created_at"),
                            rs.getTimestamp("updated_at"),
                            rs.getInt("user_id")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }

    public boolean addComplaint(Complaint complaint) {
        String sql = "INSERT INTO complaints (title, description, user_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint.getTitle());
            stmt.setString(2, complaint.getDescription());
            stmt.setInt(3, complaint.getUserId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateComplaint(Complaint complaint) {
        String sql = "UPDATE complaints SET status = ?, remarks = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, complaint.getStatus());
            stmt.setString(2, complaint.getRemarks());
            stmt.setInt(3, complaint.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteComplaint(int complaintId) {
        String sql = "DELETE FROM complaints WHERE id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, complaintId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
