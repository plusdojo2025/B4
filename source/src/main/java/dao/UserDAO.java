package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.User;

public class UserDAO {

    // users_id に一致するすべてのユーザー（重複可）を取得
    public List<User> findByUsersId(int usersId) {
        List<User> userList = new ArrayList<>();
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            String sql = "SELECT * FROM users WHERE users_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, usersId);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsers_id(rs.getString("users_id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setTypeId(rs.getInt("type_id"));
                user.setMailAddress(rs.getString("mail_address"));
                user.setGrade(rs.getInt("grade"));
                user.setSchoolClass(rs.getInt("school_class"));
                user.setTrophyId(rs.getInt("trophy_id"));
                user.setStatusesId(rs.getInt("statuses_id"));

                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return userList;
    }

    
    public User findByPrimaryKey(int id) {
        Connection conn = null;
        User user = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);

            ResultSet rs = pStmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsers_id(rs.getString("users_id"));
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
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return user;
    }

    // 1件だけを取得（重複なし前提）
    public User findByUsersIdSingle(int usersId) {
        List<User> users = findByUsersId(usersId);
        return users.isEmpty() ? null : users.get(0);
    }





	public List<User> findByUserId(String usersId) {
		List<User> userList = new ArrayList<>();
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password");

            String sql = "SELECT * FROM users WHERE users_id = ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, usersId);

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsers_id(rs.getString("users_id")); // ← DTOもStringに対応させる必要あり
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setTypeId(rs.getInt("type_id"));
                user.setMailAddress(rs.getString("mail_address"));
                user.setGrade(rs.getInt("grade"));
                user.setSchoolClass(rs.getInt("school_class"));
                user.setTrophyId(rs.getInt("trophy_id"));
                user.setStatusesId(rs.getInt("statuses_id"));

                userList.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
        }

        return userList;
	}

}

