package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Level implements Serializable {
	private int id;
	private int level;
	private int progress_id;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getProgress_id() {
		return progress_id;
	}
	public void setProgress_id(int progress_id) {
		this.progress_id = progress_id;
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
	
	public Level() {
		this.id = 0;
		this.level = 0;
		this.progress_id = 0;
	}
	
	public Level(int id, int level, int progress_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.level = level;
		this.progress_id = progress_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}