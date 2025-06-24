package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.IdPw;

public class IdPwDAO {

	public boolean isLoginOK(IdPw idpw) {
	    if (idpw.getId() == null || idpw.getId().isEmpty() ||
	        idpw.getPw() == null || idpw.getPw().isEmpty()) {
	        return false;
	    }

	    Connection conn = null;
	    boolean result = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
	            "root", "password");

	        String sql = "SELECT * FROM users WHERE users_id = ? AND password = ?";
	        PreparedStatement pStmt = conn.prepareStatement(sql);

	        // ここでusers_idは整数なのでsetIntを使う
	        pStmt.setInt(1, Integer.parseInt(idpw.getId()));
	        pStmt.setString(2, idpw.getPw());

	        ResultSet rs = pStmt.executeQuery();
	        result = rs.next();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) try { conn.close(); } catch (Exception e) { e.printStackTrace(); }
	    }

	    return result;
	}
}