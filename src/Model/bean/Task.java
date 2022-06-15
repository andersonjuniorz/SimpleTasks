package Model.bean;

public class Task {

    private int id;
    private String descr;
    private String priority;
    private String dueDate;
    private boolean done;
    private int fk_cat_id;
    private int fk_user_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getFk_cat_id() {
        return fk_cat_id;
    }

    public void setFk_cat_id(int fk_cat_id) {
        this.fk_cat_id = fk_cat_id;
    }

    public int getFk_user_id() {
        return fk_user_id;
    }

    public void setFk_user_id(int fk_user_id) {
        this.fk_user_id = fk_user_id;
    }
}
