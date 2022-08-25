package entities;

import lombok.Data;

@Data
public class User {

    private int id;
    private String email;
    private String username;
    private String pass;

    public void setUser(String username, String pass, String email) {
        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public void setUser(Integer id, String username, String pass) {
        this.id = id;
        this.username = username;
        this.pass = pass;
    }

    public void setUser(String username, String pass) {
        this.username = username;
        this.pass = pass;
    }
}
