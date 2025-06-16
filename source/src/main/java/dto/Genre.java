package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Genre implements Serializable {
	private int id;
	private String genre_name;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
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
	
	public Genre() {
		this.id = 0;
		this.genre_name = "";
	}
	public Genre(int id, String genre_name, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.genre_name = genre_name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}