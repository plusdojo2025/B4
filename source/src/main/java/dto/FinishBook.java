package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FinishBook implements Serializable {
	private int id;
	private int book_id;
	private int user_id;
	private String users_id;
	private int type_id;
	private String cover;
	private String title;
	private String type;
	LocalDateTime created_at;
	LocalDateTime updated_at;
	
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
	
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public FinishBook() {
		this.id = 0;
		this.book_id = 0;
		this.user_id = 0;
		this.users_id = "";
		this.type_id = 0;
		this.type = "";
		this.cover = "";
		this.title = "";
	}
	
	public FinishBook(int id, int book_id, int user_id, int type_id, String type, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		
		this.type = type;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public FinishBook(int id, int book_id, int user_id, int type_id, String cover, String title, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.type_id = type_id;
		this.cover = cover;
		this.title = title;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public FinishBook(int id, int book_id, int user_id, String users_id, int type_id, String cover, String title, LocalDateTime created_at,
			LocalDateTime updated_at) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.users_id = users_id;
		this.type_id = type_id;
		this.cover = cover;
		this.title = title;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}