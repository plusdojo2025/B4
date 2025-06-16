package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Recommends implements Serializable {
	private int id;
	private int book_id;
	private int user_id;
	private String comment;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
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
	
	public Recommends() {
		this.id = 0;
		this.book_id = 0;
		this.user_id = 0;
		this.comment = "";
	}
	
	public Recommends(int id, int book_id, int user_id, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.comment = comment;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}