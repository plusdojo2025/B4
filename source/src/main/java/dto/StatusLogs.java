package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class StatusLogs implements Serializable {
	private int id;
	private int user_id;
	private int status_id;
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
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
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
	
	public StatusLogs() {
		this.id = 0;
		this.user_id = 0;
		this.status_id = 0;
	}
	
	public StatusLogs(int id, int user_id, int status_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.status_id = status_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}