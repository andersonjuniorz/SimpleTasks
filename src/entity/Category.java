package entity;

import lombok.Data;

@Data
public class Category {

    private int cat_id;
    private String cat_name;
    private int fk_User;

    public Category() {}
}
