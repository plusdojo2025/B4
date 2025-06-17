package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Trophies implements Serializable {
	private int id;
	private String trophy_photo;
	private int ranking_id;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrophy_photo() {
		return trophy_photo;
	}
	public void setTrophy_photo(String trophy_photo) {
		this.trophy_photo = trophy_photo;
	}
	public int getRanking_id() {
		return ranking_id;
	}
	public void setRanking_id(int ranking_id) {
		this.ranking_id = ranking_id;
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
	
	public Trophies() {
		this.id = 0;
		this.trophy_photo = "";
		this.ranking_id = 0;
	}
	
	public Trophies(int id, String trophy_photo, int ranking_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.trophy_photo = trophy_photo;
		this.ranking_id = ranking_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}