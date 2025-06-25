package dto;

import java.time.LocalDateTime;

public class User {
    private int id;
    private int typeId;
    private String users_id;
    private String name;
    private String password;
    private String mail_address;
    private int grade;
    private int schoolClass;
    private int trophyId;
    private int statusesId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User() {
        this.id = 0;
        this.typeId = 0;
        this.users_id = "";
        this.name = "";
        this.password = "";
        this.mail_address = "";
        this.grade = 0;
        this.schoolClass = 0;
        this.trophyId = 0;
        this.statusesId = 0;
    }

    public User(int id, int typeId, String users_id, String name, String password, String mail_address, int grade, int schoolClass, int trophyId, int statusesId) {
        this.id = id;
        this.typeId = typeId;
        this.users_id = users_id;
        this.name = name;
        this.password = password;
        this.mail_address = mail_address;
        this.grade = grade;
        this.schoolClass = schoolClass;
        this.trophyId = trophyId;
        this.statusesId = statusesId;
    }

    public int getId() { 
    	return id; 
    	}
    public void setId(int id) { 
    	this.id = id; 
    	}

    public int getTypeId() { 
    	return typeId;
    	}
    public void setTypeId(int typeId) { 
    	this.typeId = typeId;
    	}

    public String getUsers_id() {
    	return users_id;
    	}
    public void setUsers_id(String users_id) {
    	this.users_id = users_id;
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

    public String getMailAddress() { 
    	return mail_address; 
    	}
    public void setMailAddress(String mail_address) { 
    	this.mail_address = mail_address; 
    	}

    public int getGrade() { 
    	return grade;
    	}
    public void setGrade(int grade) {
    	this.grade = grade;
    	}

    public int getSchoolClass() {
    	return schoolClass;
    	}
    public void setSchoolClass(int schoolClass) { 
    	this.schoolClass = schoolClass;
    	}

    public int getTrophyId() { 
    	return trophyId;
    	}
    public void setTrophyId(int trophyId) { 
    	this.trophyId = trophyId;
    	}

    public int getStatusesId() { 
    	return statusesId;
    	}
    public void setStatusesId(int statusesId) { 
    	this.statusesId = statusesId;
    	}

    public LocalDateTime getCreatedAt() { 
    	return createdAt;
    	}
    public void setCreatedAt(LocalDateTime createdAt) { 
    	this.createdAt = createdAt;
    	}

    public LocalDateTime getUpdatedAt() { 
    	return updatedAt; 
    	}
    public void setUpdatedAt(LocalDateTime updatedAt) {
    	this.updatedAt = updatedAt; 
    	}


	public User(int id) {
		this.id = id;
	}
}