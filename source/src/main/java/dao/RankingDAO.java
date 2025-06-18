package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dto.Ranking;

public class RankingDAO{
	public List<Ranking> select(Ranking Rank){
		List<Ranking>RankList = new ArrayList<Ranking>();
		
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
	                " r.id AS ranking_id, r.Ranking_kind, r.user_id, r.rank_value, r.created_at AS r_created_at, r.updated_at AS r_updated_at, " +
	                " k.id AS kind_id, k.name, k.type, k.genre_id, k.term, k.created_at AS k_created_at, k.updated_at AS k_updated_at " +
	                "FROM rankings r " +
	                "JOIN ranking_kinds k ON r.Ranking_kind = k.id " +
	                "ORDER BY r.rank_value DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Ranking ranking = new Ranking( rs.getInt("ranking_id"),
	                    rs.getInt("Ranking_kind"),
	                    rs.getInt("user_id"),
	                    rs.getInt("rank_value"),
	                    toLocalDateTime(rs.getTimestamp("r_created_at")),
	                    toLocalDateTime(rs.getTimestamp("r_updated_at")),
	                    rs.getInt("kind_id"),
	                    rs.getString("name"),
	                    rs.getString("type"),
	                    rs.getInt("genre_id"),
	                    rs.getString("term"),
	                    toLocalDateTime(rs.getTimestamp("k_created_at")),
	                    toLocalDateTime(rs.getTimestamp("k_updated_at"))
	                ););
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