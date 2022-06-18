package Model.bean;

public class User {

    private int id;
    private String email;
    private String username;
    private String pass;
    private String recoveryCode;

    public void setUser(String username, String pass, String email, String recoveryCode) {
        this.username = username;
        this.pass = pass;
        this.email = email;
        this.recoveryCode = recoveryCode;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }

    public void setRecoveryCode(String recoveryCode) {
        this.recoveryCode = recoveryCode;
    }
}
