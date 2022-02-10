package Model;

public class Task {

    private int id;
    private String task;
    private String date;
    private String timeStart;
    private String timeEnd;
    private int fk_category;
    private int fk_user;

    public Task(int id, String task, String date, String timeStart, String timeEnd, int fk_category, int fk_user) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.fk_category = fk_category;
        this.fk_user = fk_user;
    }

    public Task(int id, String task){
        this.id = id;
        this.task = task;
    }

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getFk_category() {
        return fk_category;
    }

    public void setFk_category(int fk_category) {
        this.fk_category = fk_category;
    }

    public int getFk_user() {
        return fk_user;
    }

    public void setFk_user(int fk_user) {
        this.fk_user = fk_user;
    }
}
