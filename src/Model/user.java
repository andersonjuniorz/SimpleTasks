package Model;

public class user {
    private int id;
    private String user;
    private String password;
    private String task;

    public user(int id, String user, String password, String task) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.task = task;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
    
    
    
}
