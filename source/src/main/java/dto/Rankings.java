package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Rankings implements Serializable {
	private int id;
	private int ranking_kind;
	private int user_id;
	private int rank;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRanking_kind() {
		return ranking_kind;
	}
	public void setRanking_kind(int ranking_kind) {
		this.ranking_kind = ranking_kind;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
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
	
	public Rankings() {
		this.id = 0;
		this.ranking_kind = 0;
		this.user_id = 0;
		this.rank = 0;
	}
	public Rankings(int id, int ranking_kind, int user_id, int rank, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.ranking_kind = ranking_kind;
		this.user_id = user_id;
		this.rank = rank;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}