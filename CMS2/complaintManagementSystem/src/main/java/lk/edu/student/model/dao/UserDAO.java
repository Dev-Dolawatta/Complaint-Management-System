package lk.edu.student.model.dao;

import lk.edu.student.model.User;
import lk.edu.student.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    User user = null;

    public User getUserByUsernameAndPassword(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("full_name")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
