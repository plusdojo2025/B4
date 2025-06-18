package dto;

import java.time.LocalDateTime;

public class User {
    private int id;
    private int typeId;
    private String name;
    private String password;
    private String mailAddress;
    private int grade;
    private int schoolClass; // カラム名はschool_classなので、Java側はschoolClassに
    private int trophyId;
    private int statusesId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getter / setter

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
        return mailAddress;
    }
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
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
}
