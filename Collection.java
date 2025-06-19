package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Collection implements Serializable  {

private int id;
private String statusName;
private String trophyPhoto;
private int rankingId;
private String ranking_kindName;
private String type;
private String genreName;
private int userId;
private int statusId;
private LocalDateTime createdAt;
private LocalDateTime updatedAt;


//引数なしコンストラクタ（全フィールドデフォルト値）
public Collection() {
    this.id = 0;
    this.statusName = "";
    this.trophyPhoto = "";
    this.rankingId = 0;
    this.ranking_kindName = "";
    this.type = "";
    this.genreName = "";
    this.userId = 0;
    this.statusId = 0;
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
}

public Collection(int id, String statusName, String trophyPhoto, int rankingId,
                  String ranking_kindName, String type,
                  String genreName, int userId, int statusId) {
   
}

/**
 * @return id
 */
public int getId() {
	return id;
}

/**
 * @param id セットする id
 */
public void setId(int id) {
	this.id = id;
}

/**
 * @return statusName
 */
public String getStatusName() {
	return statusName;
}

/**
 * @param statusName セットする statusName
 */
public void setstatusName(String statusName) {
	this.statusName = statusName;
}

/**
 * @return trophyPhoto
 */
public String getTrophyPhoto() {
	return trophyPhoto;
}

/**
 * @param trophyPhoto セットする trophyPhoto
 */
public void setTrophyPhoto(String trophyPhoto) {
	this.trophyPhoto = trophyPhoto;
}

/**
 * @return rankingId
 */
public int getRankingId() {
	return rankingId;
}

/**
 * @param rankingId セットする rankingId
 */
public void setRankingId(int rankingId) {
	this.rankingId = rankingId;
}

/**
 * @return genreName
 */
public String getGenreName() {
	return genreName;
}

/**
 * @param genreName セットする genreName
 */
public void setGenreName(String genreName) {
	this.genreName = genreName;
}

/**
 * @return userId
 */
public int getUserId() {
	return userId;
}

/**
 * @param userId セットする userId
 */
public void setUserId(int userId) {
	this.userId = userId;
}

/**
 * @return statusId
 */
public int getStatusId() {
	return statusId;
}

/**
 * @param statusId セットする statusId
 */
public void setStatusId(int statusId) {
	this.statusId = statusId;
}

/**
 * @return createdat
 */
public LocalDateTime getCreatedat() {
	return createdAt;
}

/**
 * @param createdat セットする createdat
 */
public void setCreatedat(LocalDateTime createdat) {
	this.createdAt = createdat;
}

/**
 * @return ranking_kindName
 */
public String getRanking_kindName() {
	return ranking_kindName;
}

/**
 * @param ranking_kindName セットする ranking_kindName
 */
public void setRanking_kindName(String ranking_kindName) {
	this.ranking_kindName = ranking_kindName;
}

/**
 * @return type
 */
public String getType() {
	return type;
}

/**
 * @param type セットする type
 */
public void setType(String type) {
	this.type = type;
}

/**
 * @return updateat
 */
public LocalDateTime getUpdateat() {
	return updatedAt;
}

/**
 * @param updateat セットする updateat
 */
public void setUpdateat(LocalDateTime updateat) {
	this.updatedAt = updateat;
}

}