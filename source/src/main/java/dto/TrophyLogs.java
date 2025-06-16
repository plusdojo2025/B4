package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TrophyLogs implements Serializable {
	private int id;
	private int user_id;
	private int trophy_id;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTrophy_id() {
		return trophy_id;
	}
	public void setTrophy_id(int trophy_id) {
		this.trophy_id = trophy_id;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	
	public TrophyLogs() {
		this.id = 0;
		this.user_id = 0;
		this.trophy_id = 0;
		
	}
	
	public TrophyLogs(int id, int user_id, int trophy_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.trophy_id = trophy_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}