package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.FinishBook;

public class FinishBookDAO {

	// 指定ユーザーが本に対して登録済みかどうかを返す（type_id を取得）
	public int getStatusId(int userId, int bookId) {
		Connection conn = null;
		int typeId = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQLを準備
			String sql = "SELECT type_id FROM finish_books WHERE user_id = ? AND book_id = ? ORDER BY updated_at DESC LIMIT 1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, bookId);

			// 実行
			ResultSet rs = pStmt.executeQuery();
			if (rs.next()) {
				typeId = rs.getInt("type_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 接続解除
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return typeId;
	}
	
	public List<FinishBook> selectNew(int user_id, int type_id) {
		Connection conn = null;
		List<FinishBook> finishBookList = new ArrayList<FinishBook>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			String sql = "SELECT * FROM finish_books JOIN books ON finish_books.book_id = books.id WHERE user_id = ? AND type_id = 1 ORDER BY updated_at DESC LIMIT 1 OFFSET 0 ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			pStmt.setInt(2, type_id);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FinishBook finishBook = new FinishBook(
						rs.getInt("id"),  
						rs.getInt("book_id"), 
						rs.getInt("user_id"),
						rs.getInt("type_id"), 
						rs.getString("title"),
						rs.getString("cover"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime()
						);
				finishBookList.add(finishBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			finishBookList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			finishBookList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					finishBookList = null;
				}
			}
		}

		// 結果を返す
		return finishBookList;
		
	}

	// 「この本を読む」ボタンでtype_id = 1 を登録
	public boolean insert(int userId, int bookId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQLを準備
			String sql = "INSERT INTO finish_books (user_id, book_id, type_id, created_at, updated_at) "
					   + "VALUES (?, ?, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, bookId);

			// 実行
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 接続解除
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	// 「この本を読む」ボタンでtype_id = 1 を登録
	public boolean update(int userId, int bookId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQLを準備
			String sql = " INTO finish_books (user_id, book_id, type_id, created_at, updated_at) "
					   + "VALUES (?, ?, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);
			pStmt.setInt(2, bookId);

			// 実行
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 接続解除
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
	
	public List<FinishBook> selectAll() {
		Connection conn = null;
		List<FinishBook> finishBookList = new ArrayList<FinishBook>();

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "SELECT * FROM finish_books JOIN books ON finish_books.book_id = books.id";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FinishBook finishBook = new FinishBook(
						rs.getInt("id"),  
						rs.getInt("book_id"), 
						rs.getInt("user_id"),
						rs.getInt("type_id"), 
						rs.getString("title"),
						rs.getString("cover"),
						rs.getTimestamp("created_at").toLocalDateTime(),
						rs.getTimestamp("updated_at").toLocalDateTime()
						);
				finishBookList.add(finishBook);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			finishBookList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			finishBookList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					finishBookList = null;
				}
			}
		}
		// 結果を返す
		return finishBookList;
	}
}