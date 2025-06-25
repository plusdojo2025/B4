package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FinishBook;
import dto.Progress;

public class ParentDAO {
	public List<Progress> selectParent(String users_id) {
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
			String sql = "SELECT progress.id, user_id, book_id, target_page, read_page, progress.created_at, progress.updated_at, MONTH(progress.updated_at) as month, DAY(progress.updated_at) as day FROM progress JOIN users ON progress.user_id = users.id WHERE users_id =?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, users_id);
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getString("users_id"),
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
	
	public List<Progress> selectTodayParent(String users_id, int book_id) {
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
					+ "WHERE users.users_id =? AND users.type_id = 3 AND book_id =? "
					+ "AND MONTH(progress.updated_at) = MONTH(CURRENT_DATE()) AND DAY(progress.updated_at) = DAY(CURRENT_DATE())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, users_id);
			pStmt.setInt(2, book_id);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Progress progress = new Progress(
						rs.getInt("id"), 
						rs.getInt("user_id"), 
						rs.getString("users_id"),
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
	
	public List<FinishBook> selectNewParent(String users_id) {
		Connection conn = null;
		List<FinishBook> finishBookNewList = new ArrayList<FinishBook>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			String sql = "SELECT b.id, f.book_id, f.user_id, f.type_id, b.title, b.cover, f.created_at, f.updated_at FROM finish_books f JOIN books b ON f.book_id = b.id WHERE u.users_id = ? AND f.type_id = 1 ORDER BY f.updated_at DESC LIMIT 1 OFFSET 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, users_id);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FinishBook finishBook = new FinishBook(
						rs.getInt("id"),  
						rs.getInt("book_id"), 
						rs.getInt("user_id"),
						rs.getString("users_id"),
						rs.getInt("type_id"), 
						rs.getString("cover"),
						rs.getString("title"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime()
						);
				finishBookNewList.add(finishBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			finishBookNewList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			finishBookNewList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					finishBookNewList = null;
				}
			}
		}

		// 結果を返す
		return finishBookNewList;
		
	}
	
	public List<FinishBook> selectNewListParent(String users_id) {
		Connection conn = null;
		List<FinishBook> finishBookNewList = new ArrayList<FinishBook>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			String sql = "SELECT b.id, f.book_id, f.user_id, f.type_id, b.title, b.cover, f.created_at, f.updated_at FROM finish_books f JOIN books b ON f.book_id = b.id WHERE u.users_id = ? AND f.type_id = 1 ORDER BY f.updated_at DESC LIMIT 100 OFFSET 1 ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, users_id);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FinishBook finishBook = new FinishBook(
						rs.getInt("id"),  
						rs.getInt("book_id"), 
						rs.getInt("user_id"),
						rs.getString("users_id"),
						rs.getInt("type_id"), 
						rs.getString("cover"),
						rs.getString("title"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime()
						);
				finishBookNewList.add(finishBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			finishBookNewList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			finishBookNewList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					finishBookNewList = null;
				}
			}
		}

		// 結果を返す
		return finishBookNewList;
		
	}
	
}