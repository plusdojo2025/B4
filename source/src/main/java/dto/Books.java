package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Books implements Serializable {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private int user_id;
	private String get;
	private int genre_id;
	private String cover;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getGet() {
		return get;
	}
	public void setGet(String get) {
		this.get = get;
	}
	public int getGenre_id() {
		return genre_id;
	}
	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
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
	public Books() {
		this.id = 0;
		this.title = "";
		this.author = "";
		this.publisher = "";
		this.user_id = 0;
		this.get = "";
		this.genre_id = 0;
		this.cover = "";
	}
	public Books(int id, String title, String author, String publisher, int user_id, String get, int genre_id,
			String cover, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.user_id = user_id;
		this.get = get;
		this.genre_id = genre_id;
		this.cover = cover;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	
}