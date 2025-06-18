package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ranking implements Serializable {
	private int id;							/*ランキングの記録*/
	private int ranking_kind;				/*ランキングの種類のID*/
	private String ranking_type;			/*ランキングの種類*/
	private String ranking_name;			/*ランキングの名前*/	
	private int genre_id;					/*ジャンル*/
	private String term;					/*期間*/
	private int user_id;					/*ユーザーID*/
	private int rank;						/*順位*/
	private LocalDateTime created_at;		/*登録日時*/	  
	private LocalDateTime update_at;		/*更新日時*/
	
	
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

	public String getRanking_type() {
		return ranking_type;
	}


	public void setRanking_type(String ranking_type) {
		this.ranking_type = ranking_type;
	}


	public String getRanking_name() {
		return ranking_name;
	}


	public void setRanking_name(String ranking_name) {
		this.ranking_name = ranking_name;
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


	public LocalDateTime getUpdate_at() {
		return update_at;
	}


	public void setUpdate_at(LocalDateTime update_at) {
		this.update_at = update_at;
	}

	public Ranking(int id, int ranking_kind, String ranking_type, String ranking_name, int genre_id, String term, int user_id, int rank, LocalDateTime created_at, LocalDateTime update_at) {
		super();
		this.id = id;
		this.ranking_kind = ranking_kind;
		this.ranking_type = ranking_type;
		this.ranking_name = ranking_name;
		this.genre_id = genre_id;
		this.term = term;
		this.user_id = user_id;
		this.rank = rank;
		this.created_at = created_at;
		this.update_at = update_at;
	}
	public Ranking() {
		super();
		this.id = 0;
		this.ranking_kind = 0;
		this.ranking_type = "";
		this.ranking_name = "";
		this.genre_id = 0;
		this.term = "";
		this.user_id = 0;
		this.rank = 0;
	}
	
}