package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reaction implements Serializable {
	private int id;
	private int reaction;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReaction() {
		return reaction;
	}
	public void setReaction(int reaction) {
		this.reaction = reaction;
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
	
	public Reaction() {
		this.id = 0;
		this.reaction = 0;
		
	}
	public Reaction(int id, int reaction, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.reaction = reaction;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}