package dao;

import entity.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TaskDAO {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/todolist?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "!Cha12BrJunior";

    /* Connect */
    public Connection Connect() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);

        } finally {
            return connection;
        }
    }

    /* CRIAR TAREFA */
    public void insertTask(Task task) {
        String insert = "insert into tb_task (done,descr,priority,dueDate,fk_id_category) values (?,?,?,?,?)";
        Connection conn = Connect();

        try {
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setBoolean(1, task.isDone());
            pst.setString(2, task.getDescr());
            pst.setString(3, task.getPriority());
            pst.setString(4, task.getDueDate());
            pst.setInt(5, task.getFk_cat_id());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: " + e);
        }
    }

    /* EXIBE TODAS AS TAREFAS DE UMA CATEGORIA X */
    public void ReadTasks(JTable tb_category,Task task) {
        DefaultTableModel dtm = (DefaultTableModel) tb_category.getModel();
        String read = "SELECT done,descr,priority,dueDate FROM tb_task WHERE fk_id_category=? ORDER BY task_id DESC";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, task.getFk_cat_id());
            //pst.setInt(2, task.getFk_user_id());
            ResultSet rs = pst.executeQuery();
            dtm.setRowCount(0);

            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getBoolean(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)}
                );
            }

            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }
    /* ALTEREI O SQL - VERIFIQUE SE ESTÁ FUNCIONANDO */
    
    /* LE O ID DA CATEGORIA */
    public void ReadTaskIDWhereName(Task task) {
        String read = "SELECT task_id FROM tb_task WHERE fk_id_category=? AND descr=?";
        //String read = "SELECT task_id FROM tb_task WHERE fk_id_category=? AND fk_id_user=? AND descr=?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, task.getFk_cat_id());
            //pst.setInt(2, task.getFk_user_id());
            pst.setString(2, task.getDescr());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                task.setId(rs.getInt(1));
            }
            
            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }

    /* SELECT POR ID */
    public void SelectFromID(Task task) {
        String read = "SELECT descr,priority,dueDate,done,fk_id_category FROM tb_task WHERE task_id=?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, task.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                task.setDescr(rs.getString(1));
                task.setPriority(rs.getString(2));
                task.setDueDate(rs.getString(3));
                task.setDone(rs.getBoolean(4));
                task.setFk_cat_id(rs.getInt(5));
            }
            
            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }

    /* DELETE */
    public void deleteTask(Task task) {
        String delete = "DELETE FROM tb_task WHERE task_id=?";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setInt(1, task.getId());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no delete: " + e);
        }
    }
    
    /* EDITAR A TAREFA */
    public void UpdateTask(Task task) {
        String update = "UPDATE tb_task SET done=?,descr=?,priority=?,dueDate=? WHERE task_id=? AND fk_id_category=?;";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setBoolean(1, task.isDone());
            pst.setString(2, task.getDescr());
            pst.setString(3, task.getPriority());
            pst.setString(4, task.getDueDate());
            pst.setInt(5, task.getId());
            pst.setInt(6, task.getFk_cat_id());           
            pst.executeUpdate();
                       
            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na edição: " + e);
        }
    }
}