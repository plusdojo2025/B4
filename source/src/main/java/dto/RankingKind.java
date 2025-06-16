package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RankingKind implements Serializable {
	private int id;
	private String name;
	private String type;
	private int genre_id;
	private String term;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
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
	
	public RankingKind() {
		this.id = 0;
		this.name = "";
		this.type = "";
		this.genre_id = 0;
		this.term = "";
	}
	
	public RankingKind(int id, String name, String type, int genre_id, String term, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.genre_id = genre_id;
		this.term = term;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
}