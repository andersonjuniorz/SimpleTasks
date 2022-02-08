package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;

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
            return connection;

        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return null;
        }
    }

    /* Insert */
    public void InsertTask(Task task) {
        String insert = "insert into tasks (task_name,fk_id_user) values (?,?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, task.getTask());
            pst.setString(2, task.getId());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (Exception e) {
            System.out.print("Erro: " + e);
        }
    }
    
    
    
    /* SELECT */
    public ArrayList<Task> ReadTasks(){
        	ArrayList<Task> task = new ArrayList<>();
		String read = "select * from Tasks;";

		try {
			Connection connection = Connect();
			PreparedStatement pst = connection.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
                        
			while (rs.next()) {
				String id = String.valueOf(rs.getInt(1));
				String tsk = rs.getString(2);
				task.add(new Task(id,tsk));
			}
			connection.close();
			pst.close();
			rs.close();

			return task;

		} catch (Exception e) {
			System.out.print("Erro: " + e);
			return null;
		}
    }
    
    
    
    
    
    
    
    
    
    
}
