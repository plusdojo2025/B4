package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Families implements Serializable {
	private int id;
	private int user1_id;
	private int user2_id;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(int user1_id) {
		this.user1_id = user1_id;
	}
	public int getUser2_id() {
		return user2_id;
	}
	public void setUser2_id(int user2_id) {
		this.user2_id = user2_id;
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
	public Families() {
		this.id = 0;
		this.user1_id = 0;
		this.user2_id = 0;
	}
	
	public Families(int id, int user1_id, int user2_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user1_id = user1_id;
		this.user1_id = user2_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}