package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.IdPw;

public class IdPwDAO {
    private final String url = "jdbc:mysql://localhost:3306/B4?useSSL=false&serverTimezone=GMT%2B9&characterEncoding=utf8";
    private final String user = "root";
    private final String password = "password";

    // ログイン
    public boolean login(IdPw idpw) {
        boolean isAuthenticated = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "SELECT COUNT(*) FROM users WHERE id = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, idpw.getId());
                stmt.setString(2, idpw.getPw());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        isAuthenticated = true;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return isAuthenticated;
    }

    // 区分を取得
    public int getUserTypeById(String id) {
        int typeId = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "SELECT type_id FROM users WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    typeId = rs.getInt("type_id");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return typeId;
    }
}


