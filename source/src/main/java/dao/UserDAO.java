package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.User;

public class UserDAO {

    // users_idを文字列で受け取りUserオブジェクトを返す
    public User findById(String id) {
        Connection conn = null;
        User user = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/B4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            String sql = "SELECT * FROM users WHERE users_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, Integer.parseInt(id));


            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUsers_id(rs.getInt("users_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setTypeId(rs.getInt("type_id"));
                user.setMailAddress(rs.getString("mail_address"));
                user.setGrade(rs.getInt("grade"));
                user.setSchoolClass(rs.getInt("school_class"));
                user.setTrophyId(rs.getInt("trophy_id"));
                user.setStatusesId(rs.getInt("statuses_id"));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return user;
    }
}

