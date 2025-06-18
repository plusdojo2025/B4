package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

public class FamilyDAO {
    private final String url = "jdbc:mysql://localhost:3306/B4?useSSL=false&serverTimezone=GMT%2B9&characterEncoding=utf8";
    private final String dbUser = "root";
    private final String dbPassword = "password";

   
    public List<User> getChildrenByParentId(int parentId) {
        List<User> children = new ArrayList<>();

        String sql = "SELECT u.* FROM users u " +
                     "JOIN familys f ON u.id = f.user2_id " +
                     "WHERE f.user1_id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, parentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User child = new User();
                child.setId(rs.getInt("id"));
                child.setName(rs.getString("name"));
                child.setTypeId(rs.getInt("type_id"));
                child.setPassword(rs.getString("password"));
                children.add(child);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return children;
    }

    
    public User getParentByChildId(int childId) {
        User parent = null;

        String sql = "SELECT u.* FROM users u " +
                     "JOIN familys f ON u.id = f.user1_id " +
                     "WHERE f.user2_id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, childId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                parent = new User();
                parent.setId(rs.getInt("id"));
                parent.setName(rs.getString("name"));
                parent.setTypeId(rs.getInt("type_id"));
                parent.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return parent;
    }

   
    public int getFamilyIdByUserId(int userId) {
        int familyId = -1;

        String sql = "SELECT id FROM familys WHERE user1_id = ? OR user2_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, userId);
                stmt.setInt(2, userId);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    familyId = rs.getInt("id");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return familyId;
    }
}

