package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Book;

public class BookDAO {
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
                    rs.getString("genre_name"), // ← genresテーブルから取得
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
    public Book findById(int id) {
        Connection conn = null;
        Book book = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root", "password");

            String sql = "SELECT books.*, genres.genre_name FROM books JOIN genres ON books.genre_id = genres.id WHERE books.id = ?";
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
                    rs.getTimestamp("created_at").toLocalDateTime(),
                    rs.getTimestamp("updated_at").toLocalDateTime()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
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

    
//    //件数取得
//    public int countAllBooks(String title, Integer genre_id) {
//        Connection conn = null;
//        int count = 0;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9", "root", "password");
//            
//            String sql = "SELECT COUNT(id) FROM books WHERE 1=1";
//          
//            //検索タイトルとジャンルのセット
//            if (title != null && !title.isEmpty()) {
//                sql.append(" AND books.title LIKE ?");
//            }
//            if (genre_id != null) {
//                sql.append(" AND books.genre_id = ?");
//            }
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try { if (conn != null) conn.close(); } catch (Exception e) {}
//        }
//
//        return count;
//    }
    
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
                pstmt.setInt(3, typeId);  // 例：1=未読了, 2=読了

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
}
