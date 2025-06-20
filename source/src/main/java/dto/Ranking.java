package dto;

import java.io.Serializable;

public class Ranking implements Serializable {
	private int user_id;
	private String name;
	private int page;
	private String genre_name;
	private int f_books;
	private int book_id;
	private int genre_id;
	
	
	
	public Ranking(int user_id, String name, int page, String genre_name, int f_books, int book_id, int genre_id) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.page = page;
		this.genre_name = genre_name;
		this.f_books = f_books;
		this.book_id = book_id;
		this.genre_id = genre_id;
	}

	public Ranking() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

	public int getF_books() {
		return f_books;
	}

	public void setF_books(int f_books) {
		this.f_books = f_books;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	
	
	
}