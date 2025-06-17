package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Users implements Serializable {
	private int id;
	private int user_type_id;
	private String name;
	private String password;
	private String mail_address;
	private int grade;
	private int school_class;
	private int torophy_id;
	private int status_id;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_type_id() {
		return user_type_id;
	}
	public void setUser_type_id(int user_type_id) {
		this.user_type_id = user_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getSchool_class() {
		return school_class;
	}
	public void setSchool_class(int school_class) {
		this.school_class = school_class;
	}
	public int getTorophy_id() {
		return torophy_id;
	}
	public void setTorophy_id(int torophy_id) {
		this.torophy_id = torophy_id;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
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
	
	public Users() {
		this.id = 0;
		this.user_type_id = 0;
		this.name = "";
		this.password = "";
		this.mail_address = "";
		this.grade = 0;
		this.school_class = 0;
		this.torophy_id = 0;
		this.status_id = 0;
		}
	
	public Users(int id, int user_type_id, String name, String password, String mail_address, int grade,
			int school_class, int torophy_id, int status_id, LocalDateTime created_at, LocalDateTime updated_at) {
		this.id = id;
		this.user_type_id = user_type_id;
		this.name = name;
		this.password = password;
		this.mail_address = mail_address;
		this.grade = grade;
		this.school_class = school_class;
		this.torophy_id = torophy_id;
		this.status_id = status_id;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
}