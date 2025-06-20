package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Book;

public class BookDAO {
	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(Book card) {
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
				String sql = "INSERT INTO books (title, author, publisher, gets, page, genre_id, cover, user_id, created_at, updated_at) "
				           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, card.getTitle() != null ? card.getTitle() : "");
		        pStmt.setString(2, card.getAuthor() != null ? card.getAuthor() : "");
		        pStmt.setString(3, card.getPublisher() != null ? card.getPublisher() : "");
		        pStmt.setString(4, card.getGets() != null ? card.getGets() : "");
		        pStmt.setInt(5, card.getPage());
		        pStmt.setInt(6, card.getGenre_id());
		        pStmt.setString(7, card.getCover() != null ? card.getCover() : "");
		        pStmt.setInt(8, card.getUser_id());		        
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
    // 全件取得（最大10件でページネーション）
//    public List<Book> selectAll() {
//        Connection conn = null;
//        List<Book> bookList = new ArrayList<>();
//
//        try {
//            // JDBCドライバ読み込み
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // DB接続
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
//					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
//					"root", "password");
//
//            // SQL準備
//            String sql = "SELECT books.*, genres.genre_name "
//            		+ "FROM books "
//            		+ "JOIN genres ON books.genre_id = genres.id "
//            		+ "ORDER BY books.created_at DESC "
//            		+ "LIMIT 10 OFFSET 1";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//
//            // DTOに詰めてリストに追加
//            while (rs.next()) {
//                Book book = new Book(
//                    rs.getInt("id"),
//                    rs.getInt("user_id"),
//                    rs.getInt("genre_id"),
//                    rs.getString("genre_name"),
//                    rs.getString("title"),
//                    rs.getString("author"),
//                    rs.getString("publisher"),
//                    rs.getString("gets"),
//                    rs.getInt("page"),
//                    rs.getString("cover"),
//                    rs.getTimestamp("created_at").toLocalDateTime(),
//                    rs.getTimestamp("updated_at").toLocalDateTime()
//                );
//                bookList.add(book);
//            }
//
//        } catch (SQLException | ClassNotFoundException e) {
//        	e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return bookList;
//    }
    
    //題名とジャンルで検索（ページネーション10件）
    public List<Book> search(String title, Integer genre_id, int page, int limit) {
        Connection conn = null;
        List<Book> result = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
                "root", "password"
            );
            
            StringBuilder sql = new StringBuilder(
                "SELECT books.*, genres.genre_name FROM books " +
                "JOIN genres ON books.genre_id = genres.id WHERE 1=1"
            );

            //検索タイトルとジャンルのセット
            if (title != null && !title.isEmpty()) {
                sql.append(" AND books.title LIKE ?");
            }
            if (genre_id != null) {
                sql.append(" AND books.genre_id = ?");
            }
            sql.append(" ORDER BY books.created_at DESC LIMIT ? OFFSET ?");
            
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (title != null && !title.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + title + "%");
            }
            if (genre_id != null) {
                pstmt.setInt(paramIndex++, genre_id);
            }

            int offset = (page - 1) * limit;
            pstmt.setInt(paramIndex++, limit);
            pstmt.setInt(paramIndex++, offset);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("genre_id"),
                    rs.getString("genre_name"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("gets"),
                    rs.getInt("page"),
                    rs.getString("cover"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
                );
                result.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return result;
    }
    
    //本の詳細用のid一致検索
    public static Book findById(int id) {
        Connection conn = null;
        Book book = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root", "password");

            String sql = """
                SELECT books.*, genres.genre_name, COUNT(recommends.id) AS recommend_count
                FROM books
                JOIN genres ON books.genre_id = genres.id
                LEFT JOIN recommends ON books.id = recommends.book_id
                WHERE books.id = ?
                GROUP BY books.id
            """;

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                book = new Book(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("genre_id"),
                    rs.getString("genre_name"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getString("gets"),
                    rs.getInt("page"),
                    rs.getString("cover"),
                    rs.getInt("recommend_count"),
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return book;
    }

 // 件数取得
    public int countAllBooks(String title, Integer genre_id) {
        Connection conn = null;
        int count = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                "root", "password"
            );

            // SQLのベース部分
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM books WHERE 1=1");

            // パラメータの有無に応じて WHERE 条件を追加
            if (title != null && !title.isEmpty()) {
                sql.append(" AND title LIKE ?");
            }
            if (genre_id != null) {
                sql.append(" AND genre_id = ?");
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            // プレースホルダーに値をセット
            int paramIndex = 1;
            if (title != null && !title.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + title + "%");
            }
            if (genre_id != null) {
                pstmt.setInt(paramIndex++, genre_id);
            }

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }

        return count;
    }
    
    //一覧機能(表示数10件でページネーション)
    public List<Book> selectAll(int page, int limit) {
        List<Book> bookList = new ArrayList<>();
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root", "password");


            int offset = (page - 1) * limit;
            String sql = "SELECT books.*, genres.genre_name FROM books JOIN genres ON books.genre_id = genres.id ORDER BY created_at DESC LIMIT ? OFFSET ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
              Book book = new Book(
                  rs.getInt("id"),
                  rs.getInt("user_id"),
                  rs.getInt("genre_id"),
                  rs.getString("genre_name"),
                  rs.getString("title"),
                  rs.getString("author"),
                  rs.getString("publisher"),
                  rs.getString("gets"),
                  rs.getInt("page"),
                  rs.getString("cover"),
                  rs.getTimestamp("created_at").toLocalDateTime(),
                  rs.getTimestamp("updated_at").toLocalDateTime()
              );
              bookList.add(book);
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }

        return bookList;
    }
    //この本を読むボタン押下後の本の登録機能
     public boolean insert(int bookId, int userId, int typeId) {
            Connection conn = null;
            boolean success = false;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
                    "root", "password"
                );

                String sql = "INSERT INTO finish_books (book_id, user_id, type, created_at, updated_at) " +
                             "VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, bookId);
                pstmt.setInt(2, userId);
                pstmt.setInt(3, typeId);

                int rows = pstmt.executeUpdate();
                if (rows > 0) {
                    success = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try { if (conn != null) conn.close(); } catch (Exception e) {}
            }

            return success;
        }
   //題名とジャンルで検索しておすすめ人数順で出力（ページネーション10件）
     public List<Book> searchRecommend(String title, Integer genre_id, int page, int limit) {
         Connection conn = null;
         List<Book> result = new ArrayList<>();

         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
                 "root", "password"
             );
             
             StringBuilder sql = new StringBuilder(
            	    "SELECT b.*, g.genre_name, COUNT(r.book_id) AS recommend_count " +
            	    "FROM books b " +
            	    "JOIN genres g ON b.genre_id = g.id " +
            	    "LEFT JOIN recommends r ON b.id = r.book_id " +
            	   "WHERE 1=1"
            	);

            	if (title != null && !title.isEmpty()) {
            	    sql.append(" AND b.title LIKE ?");
            	}
            	if (genre_id != null) {
            	    sql.append(" AND b.genre_id = ?");
            	}
            		sql.append(" GROUP BY b.id ");
           			sql.append(" ORDER BY recommend_count DESC LIMIT ? OFFSET ?");

             PreparedStatement pstmt = conn.prepareStatement(sql.toString());

             int paramIndex = 1;
             if (title != null && !title.isEmpty()) {
                 pstmt.setString(paramIndex++, "%" + title + "%");
             }
             if (genre_id != null) {
                 pstmt.setInt(paramIndex++, genre_id);
             }

             int offset = (page - 1) * limit;
             pstmt.setInt(paramIndex++, limit);
             pstmt.setInt(paramIndex++, offset);
             
             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 Book book = new Book(
                     rs.getInt("id"),
                     rs.getInt("user_id"),
                     rs.getInt("genre_id"),
                     rs.getString("genre_name"),
                     rs.getString("title"),
                     rs.getString("author"),
                     rs.getString("publisher"),
                     rs.getString("gets"),
                     rs.getInt("page"),
                     rs.getString("cover"),
                     rs.getInt("recommend_count"),
                     rs.getTimestamp("created_at").toLocalDateTime(),
                     rs.getTimestamp("updated_at").toLocalDateTime()
                 );
                 result.add(book);
             }

         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             try { if (conn != null) conn.close(); } catch (Exception e) {}
         }

         return result;
     }
}

