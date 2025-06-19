package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BookRecommend implements Serializable {
	private int id; 	// 番号
	private int book_id;	//本のID
	private int user_id; 	// ユーザーID
	private int type_id;	//本の状態のID
	private String type;	//本の状態(読了、未読了)
	private String comment;	//おすすめする本へのコメント
	private LocalDateTime created_at;	//登録日時
	private LocalDateTime updated_at;	//更新日時
	
	public BookRecommend(int id, int book_id, int user_id, int type_id, String type, String comment, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.comment = comment;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	public BookRecommend() {
		this.id = 0;
		this.book_id = 0;
		this.user_id = 0;
		this.type_id = 0;
		this.type = "";
		this.comment = "";
	}

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

}