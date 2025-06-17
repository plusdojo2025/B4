package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserTypes implements Serializable {
	private int id;
	private int type;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	
	public UserTypes() {
		this.id = 0;
		this.type = 0;
	}
	
	public UserTypes(int id, int type, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.type = type;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}