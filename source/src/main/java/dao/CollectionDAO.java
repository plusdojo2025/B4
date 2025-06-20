package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dto.Collection;

		public class CollectionDAO {
		 //ステータス一覧表示
				public List<Collection> selectByStatusName(String statusName, int userId) throws ClassNotFoundException {
					Connection conn = null;
					List<Collection> result = new ArrayList<>();
					try {
						// JDBCドライバを読み込む
						Class.forName("com.mysql.cj.jdbc.Driver");
						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
								+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
								"root", "password");
						
						// SQL文を準備する
						//ステータス一覧表示
						String sql = """
								
							SELECT s.name, sl.created_at
							FROM statuses_logs sl
							JOIN statuses s ON s.id = sl.statuses_id
							WHERE sl.user_id = ?
							ORDER BY sl.created_at DESC
							
							""";
						PreparedStatement pStmt = conn.prepareStatement(sql);
					
						// SQL文を完成させる
						pStmt.setInt(1, userId);
						
						// SELECT文を実行し、結果表を取得する
						ResultSet rs = pStmt.executeQuery();
						
						//結果表をコレクションにコピーする
						while(rs.next()) {
							Collection Coll = new Collection();
							Coll.setstatusName(rs.getString("statusName"));
							LocalDateTime CreatedAt=rs.getTimestamp("created_at").toLocalDateTime();
							Coll.setstatusCreatedat(CreatedAt);
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年/MM月/dd日");
						    String formatted = CreatedAt.format(formatter);
						    Coll.setStatusCreatedAtStr(formatted);
							result.add(Coll);
						}
					}
					
			
					catch (SQLException e) {
						e.printStackTrace();
						result = null;
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
					return result;
				}
				//トロフィー一覧取得
		public List<Collection> selectByTrophyPhoto(int userId) throws ClassNotFoundException {
			Connection conn = null;
			List<Collection> result = new ArrayList<Collection>();
			try {
				// JDBCドライバを読み込む
				Class.forName("com.mysql.cj.jdbc.Driver");
				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/B4?"
						+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
						"root", "password");
				
				// SQL文を準備する
				//トロフィー一覧表示
				//SQL文を準備
				String sql = """
						
				SELECT t.trophy_photo, tl.created_at
		        FROM trophy_logs tl
		        JOIN trophys t ON t.id = tl.trophy_id
		        WHERE user_id = ?
		        ORDER BY tl.created_at DESC;
		        
					""";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる
				pStmt.setInt(1, userId);
				
				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();
				
				//結果表をコレクションにコピーする
				while(rs.next()) {
					Collection Coll = new Collection();
					Coll.setTrophyPhoto(rs.getString("trophy_photo"));
					Coll.settrophyCreatedat(rs.getTimestamp("created_at").toLocalDateTime());
					result.add(Coll);
				}
			}
			
			catch (SQLException e) {
				e.printStackTrace();
				result = null;
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
			return result;
		}
		//ユーザーIDでステータス一覧を取得するメソッド
		public List<Collection> selectByUserId(int userId) throws ClassNotFoundException {
		    List<Collection> result = new ArrayList<>();
		    Connection conn = null;
		    try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conn = DriverManager.getConnection(
		            "jdbc:mysql://localhost:3306/B4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
		            "root",
		            "password"
		        );
		        String sql = """
		            SELECT s.name AS statusName, sl.created_at
		            FROM statuses_logs sl
		            JOIN statuses s ON s.id = sl.statuses_id
		            WHERE sl.user_id = ?
		            ORDER BY sl.created_at DESC
		        """;
		        PreparedStatement pStmt = conn.prepareStatement(sql);
		        pStmt.setInt(1, userId);
		        ResultSet rs = pStmt.executeQuery();
		        while (rs.next()) {
		            Collection coll = new Collection();
		            coll.setstatusName(rs.getString("statusName"));
		            coll.setstatusCreatedat(rs.getTimestamp("created_at").toLocalDateTime());
		            result.add(coll);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
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
		public List<Collection> selectByTrophyPhoto(String trophyPhoto, int i) {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}
		
		//ユーザーIDでトロフィー一覧を取得するメソッド
			public List<Collection> selectRankingId(int userId) throws ClassNotFoundException {
			    List<Collection> result = new ArrayList<>();
			    Connection conn = null;
			    try {
			        Class.forName("com.mysql.cj.jdbc.Driver");
			        conn = DriverManager.getConnection(
			            "jdbc:mysql://localhost:3306/B4?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
			            "root",
			            "password"
			        );
			        String sql = """
			            SELECT s.name AS statusName, sl.created_at
			            FROM statuses_logs sl
			            JOIN statuses s ON s.id = sl.statuses_id
			            WHERE sl.user_id = ?
			            ORDER BY sl.created_at DESC
			        """;
			        PreparedStatement pStmt = conn.prepareStatement(sql);
			        pStmt.setInt(1,userId);
			        ResultSet rs = pStmt.executeQuery();
			        while (rs.next()) {
			            Collection coll = new Collection();
			            coll.setTrophyPhoto(rs.getString("trophyPhoto"));
			            coll.settrophyCreatedat(rs.getTimestamp("created_at").toLocalDateTime());
			            result.add(coll);
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } finally {
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
			
			}
