package dto;

import java.io.Serializable;

public class Families implements Serializable {
	private int id;
	private int user_id;
	private String created_at;
	private String updated_at;
	
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public Families() {
		this.id = 0;
		this.user_id = 0;
		this.created_at = "";
		this.updated_at = "";
	}
	
	public Families(int id, int user_id, String created_at, String updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}