package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class userDAO {
    Password.Pass p = new Password.Pass();
    
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/todoList?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = ""+p.getPass()+"";

    /* Connect */
    public Connection Connect() {
        Connection connection = null;
        System.out.println(p.getPass());
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return null;
        }
    }

    /* Insert */
    public void InsertTask(user task) {
        String insert = "insert into list (task) values (?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, task.getTask());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (Exception e) {
            System.out.print("Erro: " + e);
        }
    }
    
/* read */
/* update */
/* delete */
}
