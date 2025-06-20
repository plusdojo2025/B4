package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.JsonUserBeans;

public class AjaxDAO {
	public boolean insert(JsonUserBeans card) {
		Connection conn = null;
		boolean result = false;

		try {
			 //JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			 //データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/servletTestProject", "sa", "");

			 //SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			//項目を増やす場合は(NULL,
			String sql = "INSERT INTO USERS (name,age,hobby,created_at,updated_at) VALUES (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			 //SQL文を完成させる
			if (card.getName() != null && !card.getName().equals("")) {
				pStmt.setString(1, card.getName());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (card.getAge() != 0) {
				pStmt.setInt(2, card.getAge());
			}
			else {
				pStmt.setInt(2, 0);
			}
			if (card.getHobby() != null && !card.getHobby().equals("")) {
				pStmt.setString(3, card.getHobby());
			}
			else {
				pStmt.setString(3, "（未設定）");
			}

			 //SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			 //データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		 //結果を返す
		return result;
	}

}
