package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Progress;

public class ProgressDAO {
	public List<Progress> select(Progress prog) {
		Connection conn = null;
		List<Progress> progressList = new ArrayList<Progress>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT id, user_id, target_page, read_page, MONTH(updated_at) FROM progress WHERE user_id =? AND MONTH(updated_at) = ? GROUP BY update_at ORDER BY update_at";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, prog.getUser_id());
			pStmt.setInt(2, prog.getMonth());	// SQL文を実行し、結果表を取得する
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("book_id"), 
						rs.getInt("user_id"), 
						rs.getInt("target_page"), 
						rs.getInt("read_page"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime(),
						rs.getInt("month")
						);
				progressList.add(progress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			progressList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			progressList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					progressList = null;
				}
			}
		}

		// 結果を返す
		return progressList;
	}
	
	public boolean insert(Progress prog) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Progress VALUES (0, 0, 0, ?, ?, 0, 0, 0)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			
			pStmt.setInt(1, prog.getTarget_page());
			pStmt.setInt(2, prog.getRead_page());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}
	
	
	
	
	public List<Progress> selectAll() {
		Connection conn = null;
		List<Progress> progressList = new ArrayList<Progress>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT id, book_id, user_id, target_page, read_page, FROM progress WHERE id =? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next())  {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("book_id"), 
						rs.getInt("user_id"), 
						rs.getInt("target_page"), 
						rs.getInt("read_page"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime(),
						rs.getInt("month")
						);
				progressList.add(progress);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			progressList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			progressList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					progressList = null;
				}
			}
		}

		// 結果を返す
		return progressList;
	}
}