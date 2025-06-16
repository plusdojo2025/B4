package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Opinion implements Serializable {
	private int id;
	private int user_id;
	private String comment;
	private int reaction_id;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getReaction_id() {
		return reaction_id;
	}
	public void setReaction_id(int reaction_id) {
		this.reaction_id = reaction_id;
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
	
	public Opinion() {
		this.id = 0;
		this.user_id = 0;
		this.comment = "";
		this.reaction_id = 0;
	}
	public Opinion(int id, int user_id, String comment, int reaction_id, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.comment = comment;
		this.reaction_id = reaction_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}