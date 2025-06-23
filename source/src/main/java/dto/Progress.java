package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Progress implements Serializable {
	private int id;
	private int user_id;
	private int book_id;
	private String name;
	private int target_page;
	private int read_page;
	private int grade;
	private int school_class;
	LocalDateTime created_at = LocalDateTime.now();
	LocalDateTime updated_at = LocalDateTime.now();
	public int month;
	public int day;
	
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

	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTarget_page() {
		return target_page;
	}
	public void setTarget_page(int target_page) {
		this.target_page = target_page;
	}
	public int getRead_page() {
		return read_page;
	}
	public void setRead_page(int read_page) {
		this.read_page = read_page;
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
	public int getMonth() {
		return updated_at.getMonthValue();
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return updated_at.getDayOfMonth();
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	public Progress() {
		this.id = 0;
		this.user_id = 0;
		this.book_id = 0;
		this.name = "";
		this.target_page = 0;
		this.read_page = 0;
		this.grade = 0;
		this.school_class = 0;
		this.month = 0;
		this.day = 0;
		
	}
	public Progress(int id, int user_id, int book_id, int target_page, int read_page, LocalDateTime created_at,
			LocalDateTime updated_at, int month, int day) {
		this.id = id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.target_page = target_page;
		this.read_page = read_page;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.month = month;
		this.day = day;
	}
	public Progress(int id, int user_id, int book_id, String name, int target_page, int read_page, int grade, int school_class, LocalDateTime created_at,
			LocalDateTime updated_at, int month, int day) {
		this.id = id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.name = name;
		this.target_page = target_page;
		this.read_page = read_page;
		this.grade = grade;
		this.school_class = school_class;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.month = month;
		this.day = day;
	}
	
	
}