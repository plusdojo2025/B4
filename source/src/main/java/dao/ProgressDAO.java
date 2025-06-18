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
	public List<Progress> select(int user_id, int month) {
		Connection conn = null;
		List<Progress> progressList = new ArrayList<Progress>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT progress.id, user_id, book_id, target_page, read_page, progress.created_at, progress.updated_at, MONTH(progress.updated_at) as month, DAY(progress.updated_at) as day FROM progress JOIN users ON progress.user_id = users.id WHERE user_id =? AND MONTH(progress.updated_at) = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, month);	// SQL文を実行し、結果表を取得する
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getInt("book_id"), 
						rs.getInt("target_page"), 
						rs.getInt("read_page"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime(),
						rs.getInt("month"),
						rs.getInt("day")
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
	
	public List<Progress> selectToday(int user_id, int month, int day) {
		Connection conn = null;
		List<Progress> progressList = new ArrayList<Progress>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT progress.id, user_id, book_id, target_page, read_page, progress.created_at, progress.updated_at, MONTH(progress.updated_at) as month, DAY(progress.updated_at) as day "
					+ "FROM progress JOIN users ON progress.user_id = users.id "
					+ "WHERE user_id =? AND MONTH(progress.updated_at) = ? AND DAY(progress.updated_at) = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, month);	// SQL文を実行し、結果表を取得する
			pStmt.setInt(3, day);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getInt("book_id"), 
						rs.getInt("target_page"), 
						rs.getInt("read_page"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime(),
						rs.getInt("month"),
						rs.getInt("day")
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
	
	public boolean insert_target(int target_page) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Progress VALUES (0, 0, 0, ?, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			
			pStmt.setInt(1, target_page);
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
	
	public boolean insert_read(int read_page) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO Progress VALUES (0, 0, 0, 0, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			
			pStmt.setInt(1, read_page);
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT progress.id, user_id, book_id, target_page, read_page, progress.created_at, progress.updated_at, MONTH(progress.updated_at) as month, DAY(progress.updated_at) as day FROM progress JOIN users ON progress.user_id = users.id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next())  {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getInt("book_id"), 
						rs.getInt("target_page"), 
						rs.getInt("read_page"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime(),
						rs.getInt("month"),
						rs.getInt("day")
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