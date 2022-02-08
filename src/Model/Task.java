package Model;

public class Task {

    private String id;
    private String task;

    public Task() {}

    public Task(String id, String task) {
        this.id = id;
        this.task = task;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

}
