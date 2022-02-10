package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TaskDAO {

    Password.Pass p = new Password.Pass();

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/todoList?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "" + p.getPass() + "";

    /* Connect */
    public Connection Connect() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro: " + e);
        } finally {
            return connection;
        }
    }

    /* Insert */
    public void insertTask(Task task) {
        String insert = "insert into tasks (task_name,fk_id_user) values (?,?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, task.getTask());
            pst.setInt(2, task.getId());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: "+e);
        }
    }

    /* SELECT */
    public ArrayList<Task> ReadTasks() {
        ArrayList<Task> task = new ArrayList<>();
        String read = "select * from Tasks;";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String tsk = rs.getString(2);
                //String a = null;
                task.add(new Task(id, tsk));
            }

            connection.close();
            pst.close();
            rs.close();
            return task;

        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro de leitura: "+e);
            return task;
        }
    }
    
    /* DELETE */
    public void deleteTask(Task task) {
        String delete = "delete from tasks where task_id=?";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setInt(2, task.getId());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro no delete: "+e);
        }
    }
}
