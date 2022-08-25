package entity;

import lombok.Data;

@Data
public class Task {
    
    private int id;
    private String descr;
    private String priority;
    private String dueDate;
    private boolean done;
    private int fk_cat_id;
}
