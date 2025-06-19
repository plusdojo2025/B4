package dto;

import java.io.Serializable;

public class Ranking implements Serializable {
	private int user_id;
	private String name;
	private int page;
	
	public Ranking(int user_id, String name, int page) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.page = page;
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
	
	
	
}