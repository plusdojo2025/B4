package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Ranking;

public class RankingDAO{
	public List<Ranking> selectBySchool_class(int school_class, String month){
		List<Ranking>RankList = new ArrayList<>();
		
		Connection conn = null;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL文
			String sql ="SELECT u.id AS user_id ,u.name,SUM(p.read_page) AS page FROM progress p JOIN users u ON p.user_id = u.id WHERE u.school_class = ? AND DATE_FORMAT(p.created_at, '%Y-%m') = ? GROUP BY u.id, u.name ORDER BY SUM(p.read_page) DESC";				
	
						    
						

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, school_class);
			pStmt.setString(2, month);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Ranking ranking = new Ranking();
				ranking.setUser_id(rs.getInt("user_id"));
				ranking.setName(rs.getString("name"));
			    ranking.setPage(rs.getInt("page"));
			    RankList.add(ranking); // ← リストに追加
			
                }
		} catch (SQLException e) {
			e.printStackTrace();
			RankList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			RankList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					RankList = null;
				}
			}
		}
		
		
		
		return RankList;
	}
	public List<Ranking> selectByGenre(int genre_id , String month){
		List<Ranking>RankList = new ArrayList<>();
Connection conn = null;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			// SQL文
			String sql = "SELECT u.id AS user_id, u.name, g.genre_name, COUNT(*) AS f_books FROM finish_books f JOIN users u ON f.user_id = u.id " +
					"JOIN books b ON f.book_id = b.id JOIN genres g ON b.genre_id = g.id WHERE b.genre_id = ? AND DATE_FORMAT (f.created_at, '%Y-%m') = ?  GROUP BY u.id, u.name, g.genre_name ORDER BY f_books DESC ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, genre_id);
			pStmt.setString(2, month);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Ranking ranking = new Ranking();
				ranking.setUser_id(rs.getInt("user_id"));
				ranking.setName(rs.getString("name"));
			    ranking.setGenre_name(rs.getString("genre_name"));
				ranking.setF_books(rs.getInt("f_books"));
			    RankList.add(ranking); // ← リストに追加
			
                }
		} catch (SQLException e) {
			e.printStackTrace();
			RankList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			RankList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					RankList = null;
				}
		}
}
		return RankList;	
}
	public List<Ranking> selectPopularBooksByGenre(int genreId) {
	    List<Ranking> rankList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "user", "password");

	        String sql = 
	            "SELECT b.title,  COUNT(*) AS f_books " +
	            "FROM finish_books f " +
	            "JOIN books b ON f.book_id = b.id " +
	            "WHERE b.genre_id = ? " +
	            "GROUP BY b.id, b.title " +
	            "ORDER BY f_books DESC";

	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, genreId);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            Ranking r = new Ranking();
	            r.setTitle(rs.getString("title"));
	            rankList.add(r);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (pstmt != null) pstmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return rankList;
	}
	}
