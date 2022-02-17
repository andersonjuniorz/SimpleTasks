package Model;

public class User {

    private int id;
    private int remmember;
    private String user;
    private String password;
    private String email;

    public User() {
    }

    public User(String user, String pass) {
        this.user = user;
        this.password = pass;
    }

    public User(String user, String pass, String email) {
        this.user = user;
        this.password = pass;
        this.email = email;
    }

    public User(int id, int remmember, String user, String password, String email) {
        this.id = id;
        this.remmember = remmember;
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public void setUser(String user, String pass, String email) {
        this.user = user;
        this.password = pass;
        this.email = email;
    }

    public void setUser(String user, String pass) {
        this.user = user;
        this.password = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemmember() {
        return remmember;
    }

    public void setRemmember(int remmember) {
        this.remmember = remmember;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
