package dto;

import java.io.Serializable;

public class UserTypes implements Serializable {
	private int id;
	private int type;
	private String created_at;
	private String updated_at;
	
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
	
	public UserTypes() {
		this.id = 0;
		this.type = 0;
		this.created_at = "";
		this.updated_at = "";
	}
	
	public UserTypes(int id, int type, String created_at, String updated_at) {
		this.id = id;
		this.type = type;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}