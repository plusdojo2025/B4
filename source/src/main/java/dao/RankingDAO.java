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
	public List<Ranking> select(){
		List<Ranking>RankList = new ArrayList<>();
		
		Connection conn = null;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webapp2?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");
			
			//SQL文
			String sql = "SELECT " +
	                " id, ranking_kind, user_id, rank_value, r_created_at, r_updated_at" +
	                "ranking_kind_id, name, type, genre_id, term, k_created_at, k_updated_at " +
	                "FROM rankings  " +
	                "JOIN ranking_kinds  ON ranking_kind = ranking_kind_id " +
	                "ORDER BY rank_value DESC";


			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Ranking ranking = new Ranking(
					    rs.getInt("id"),
					    rs.getInt("ranking_kind"),
					    rs.getInt("user_id"),
					    rs.getInt("rank_value"),
					    rs.getInt("ranking_kind_id"),
					    rs.getString("name"),
					    rs.getString("type"),
					    rs.getInt("genre_id"),
					    rs.getString("term"),
					    rs.getTimestamp("r_created_at").toLocalDateTime(),
					    rs.getTimestamp("r_updated_at").toLocalDateTime(),
					    rs.getTimestamp("k_created_at").toLocalDateTime(),
					    rs.getTimestamp("k_updated_at").toLocalDateTime()
					);
				RankList.add(ranking);
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