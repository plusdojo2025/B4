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
	public int getTypeId(int userId, int bookId) {
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
	
	public List<FinishBook> selectNew(int user_id) {
		Connection conn = null;
		List<FinishBook> finishBookNewList = new ArrayList<FinishBook>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			String sql = "SELECT b.id, f.book_id, f.user_id, f.type_id, b.title, b.cover, f.created_at, f.updated_at FROM finish_books f JOIN books b ON f.book_id = b.id WHERE f.user_id = ? AND f.type_id = 1 ORDER BY f.updated_at DESC LIMIT 1 OFFSET 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, user_id);
			
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				FinishBook finishBook = new FinishBook(
						rs.getInt("id"),  
						rs.getInt("book_id"), 
						rs.getInt("user_id"),
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
	
	// 読了していない finish_books から updated_at が最新の book_id を1件取得
	public Integer selectLatestReadingBookId(int userId) {
	    Connection conn = null;
	    Integer bookId = null;

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
	            + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	            "root", "password");

	        // 読了状態（type_id = 2）以外の最新の1件を取得
	        String sql = "SELECT book_id FROM finish_books WHERE user_id = ? AND type_id != 2 ORDER BY updated_at DESC LIMIT 1";
	        PreparedStatement pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, userId);

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            bookId = rs.getInt("book_id");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return bookId;
	}

	
	// すでに「この本を読む」ボタンが押されている本の日時を更新
	public boolean updateTimestamp(int userId, int bookId) {
		Connection conn = null;
		try {// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");
				String sql = "UPDATE finish_books SET updated_at = CURRENT_TIMESTAMP WHERE user_id = ? AND book_id = ?";
	         PreparedStatement pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, userId);
	        pstmt.setInt(2, bookId);

	        int affectedRows = pstmt.executeUpdate();
	        return affectedRows > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}