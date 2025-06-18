package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dto.User;

public class userDAO {
    private final String url = "jdbc:mysql://localhost:3306/B4?useSSL=false&serverTimezone=GMT%2B9&characterEncoding=utf8";
    private final String dbUser = "root";
    private final String dbPassword = "password";

    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setTypeId(rs.getInt("type_id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setMailAddress(rs.getString("mail_address"));
                    user.setGrade(rs.getInt("grade"));
                    user.setSchoolClass(rs.getInt("school_class"));
                    user.setTrophyId(rs.getInt("trophy_id"));
                    user.setStatusesId(rs.getInt("statuses_id"));

                    Timestamp createdAtTimestamp = rs.getTimestamp("created_at");
                    if (createdAtTimestamp != null) {
                        user.setCreatedAt(createdAtTimestamp.toLocalDateTime());
                    }

                    Timestamp updatedAtTimestamp = rs.getTimestamp("updated_at");
                    if (updatedAtTimestamp != null) {
                        user.setUpdatedAt(updatedAtTimestamp.toLocalDateTime());
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}

