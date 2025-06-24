package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.IdPw;
import dto.User;

public boolean isLoginOK(IdPw idpw) {
	Connection conn = null;
	boolean loginResult = false;

	try {
		// JDBCドライバを読み込む
		Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/b4?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "password");
		
		// SELECT文を準備する
					String sql = "SELECT id,type_id,name,users_id,password,mail_address,trophy_id,"
								+ "stastuses_id,created_at,updated_at"
								+"FROM B4"
								+"WHERE id LIKE ? AND type_id LIKE ? AND name LIKE ? "
								+ "users_id LIKE ? password LIKE ?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					pStmt.setString(1, idpw.getId());
					pStmt.setString(2, idpw.getPw());

					// SELECT文を実行し、結果表を取得する
					ResultSet rs = pStmt.executeQuery();

					// ユーザーIDとパスワードが一致するユーザーがいれば結果をtrueにする
					rs.next();
					if (rs.getInt("users_id") == 1) {
						loginResult = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					loginResult = false;
				} finally {
					// データベースを切断
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
							loginResult = false;
						}
					}
				}

				// 結果を返す
				return loginResult;
			}
		}


