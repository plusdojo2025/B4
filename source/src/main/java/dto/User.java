package dto;

public class User {
    private int id;
    private int typeId;
    private String name;
    private String password;

    // ゲッターとセッター
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
