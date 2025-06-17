package dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookRecommend implements Serializable {
	private int id; 	// 番号
	private int user_id; 	// ユーザーID
	private int genre_id; 	// ジャンルID
	private String genre_name;	//ジャンル名
	private int type_id;	//本の状態のID
	private String type;	//本の状態(読了、未読了)
	private String title; 	// 本のタイトル
	private String author;	//著者名
	private String publisher;	//出版社
	private String gets;	//手に入れた場所
	private int page;	//ページ数
	private String cover;	//表紙画像のファイルパス
	private LocalDateTime created_at;	//登録日時
	private LocalDateTime updated_at;	//更新日時
	
	public BookRecommend(int id, int user_id, int genre_id, String genre_name, int type_id, String type, String title, String author, String publisher, String gets, 
			int page, String cover, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.genre_id = genre_id;
		this.genre_name = genre_name;
		this.type_id = type_id;
		this.type = type;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.gets = gets;
		this.page = page;
		this.cover = cover;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public BookRecommend() {
		this.id = 0;
		this.user_id = 0;
		this.genre_id = 0;
		this.genre_name = "";
		this.title = "";
		this.author = "";
		this.publisher = "";
		this.gets = "";
		this.page = 0;
		this.cover = "";
	}

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

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	public String getGenre_Name() {
	    return genre_name;
	}

	public void setGenre_Name(String genre_name) {
	    this.genre_name = genre_name;
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

	public String getGets() {
		return gets;
	}

	public void setGets(String gets) {
		this.gets = gets;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
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
	
	public String getCreated_atStr() {
	    if (created_at == null) return "";
	    return created_at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
	}

	public String getUpdated_atStr() {
	    if (updated_at == null) return "";
	    return updated_at.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
	}
}
