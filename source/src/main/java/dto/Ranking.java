package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ranking implements Serializable {
	private int id;							/*ランキングの記録*/
	private int ranking_kind;				/*ランキングの種類のID*/
	private int user_id;					/*ユーザーID*/
	private int rank_value;					/*順位*/
	private int ranking_kind_id;			/*ランキングの種類のID（別テーブル）*/	
	private String name;					/*ランキングの名前*/	
	private String type;					/*ランキングの種類*/
	private int genre_id;					/*ジャンル*/
	private String term;					/*期間*/
	private LocalDateTime r_created_at;		/*ランキングの登録日時*/	  
	private LocalDateTime r_updated_at;		/*ランキングの更新日時*/
	private LocalDateTime k_created_at;		/*ランキングの種類の登録日時*/	  
	private LocalDateTime k_updated_at;		/*ランキングの種類の更新日時*/
	
	
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
	public int getRank_value() {
		return rank_value;
	}
	public void setRank_value(int rank_value) {
		this.rank_value = rank_value;
	}
	public int getRanking_kind_id() {
		return ranking_kind_id;
	}
	public void setRanking_kind_id(int ranking_kind_id) {
		this.ranking_kind_id = ranking_kind_id;
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
	public LocalDateTime getR_created_at() {
		return r_created_at;
	}
	public void setR_created_at(LocalDateTime r_created_at) {
		this.r_created_at = r_created_at;
	}
	public LocalDateTime getR_updated_at() {
		return r_updated_at;
	}
	public void setR_updated_at(LocalDateTime r_updated_at) {
		this.r_updated_at = r_updated_at;
	}
	
		public LocalDateTime getK_created_at() {
		return k_created_at;
	}
	public void setK_created_at(LocalDateTime k_created_at) {
		this.k_created_at = k_created_at;
	}
	public LocalDateTime getK_updated_at() {
		return k_updated_at;
	}
	public void setK_updated_at(LocalDateTime k_updated_at) {
		this.k_updated_at = k_updated_at;
	}
		public Ranking(
				int id,
			    int ranking_kind,
			    int user_id,
			    int rank_value,
			    int ranking_kind_id,
			    String name,
			    String type,
			    int genre_id,
			    String term,
			    LocalDateTime r_created_at,
			    LocalDateTime r_updated_at,
			    LocalDateTime k_created_at,
			    LocalDateTime k_updated_at) {
		this.id = id;
		this.ranking_kind = ranking_kind;
		this.user_id = user_id;
		this.rank_value = rank_value;
		this.ranking_kind_id = ranking_kind_id;
		this.name = name;
		this.type = type;
		this.genre_id = genre_id;
		this.term = term;
		this.r_created_at = r_created_at;
		this.r_updated_at = r_updated_at;
		this.k_created_at = k_created_at;
		this.k_updated_at = k_updated_at;
	}
	public Ranking() {
		this.id = 0;
		this.ranking_kind = 0;
		this.user_id = 0;
		this.rank_value = 0;
		this.ranking_kind_id = 0;
		this.name = "";
		this.type = "";
		this.genre_id = 0;
		this.term = "";
	}
	
}