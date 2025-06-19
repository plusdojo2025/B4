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
	public List<Ranking> select(int school_class){
		List<Ranking>RankList = new ArrayList<>();
		
		Connection conn = null;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL文
			String sql ="SELECT u.id AS user_id ,u.name,SUM(p.read_page) AS page FROM progress p JOIN users u ON p.user_id = u.id WHERE u.school_class = ? GROUP BY u.id, u.name ORDER BY SUM(p.read_page) DESC;";				
	
						    
						

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, school_class);
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Ranking ranking = new Ranking();
				ranking.setUser_id(rs.getInt("user_id"));
				ranking.setName(rs.getString("name"));
			    ranking.setPage(rs.getInt("page"));
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
	
	
}