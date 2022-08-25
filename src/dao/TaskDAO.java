package dao;

import connections.ConnectionFactory;
import entities.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TaskDAO {

    /* CRIAR TAREFA */
    public void insertTask(Task task) {
        String insert = "insert into tb_task (done,descr,priority,dueDate,fk_id_category) values (?,?,?,?,?)";
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;     
        
        try {
            pst = connection.prepareStatement(insert);
            pst.setBoolean(1, task.isDone());
            pst.setString(2, task.getDescr());
            pst.setString(3, task.getPriority());
            pst.setString(4, task.getDueDate());
            pst.setInt(5, task.getFk_cat_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: " + e);
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }

    /* EXIBE TODAS AS TAREFAS DE UMA CATEGORIA X */
    public void ReadTasks(JTable tb_category,Task task) {
        DefaultTableModel dtm = (DefaultTableModel) tb_category.getModel();
        String read = "SELECT done,descr,priority,dueDate FROM tb_task WHERE fk_id_category=? ORDER BY task_id DESC";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null; 
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, task.getFk_cat_id());
            rs = pst.executeQuery();
            dtm.setRowCount(0);

            while (rs.next()) {
                dtm.addRow(new Object[]{
                    rs.getBoolean(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)}
                );
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
    }
    
    /* LE O ID DA CATEGORIA */
    public void ReadTaskIDWhereName(Task task) {
        String read = "SELECT task_id FROM tb_task WHERE fk_id_category=? AND descr=?";
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;     
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, task.getFk_cat_id());
            pst.setString(2, task.getDescr());
            rs = pst.executeQuery();

            while (rs.next()) {
                task.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
    }

    /* SELECT POR ID */
    public void SelectFromID(Task task) {
        String read = "SELECT descr,priority,dueDate,done,fk_id_category FROM tb_task WHERE task_id=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;     
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, task.getId());
            rs = pst.executeQuery();

            while (rs.next()) {
                task.setDescr(rs.getString(1));
                task.setPriority(rs.getString(2));
                task.setDueDate(rs.getString(3));
                task.setDone(rs.getBoolean(4));
                task.setFk_cat_id(rs.getInt(5));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }        
    }

    /* DELETE */
    public void deleteTask(Task task) {
        String delete = "DELETE FROM tb_task WHERE task_id=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null; 
        
        try {
            pst = connection.prepareStatement(delete);
            pst.setInt(1, task.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no delete: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }
    
    /* EDITAR A TAREFA */
    public void UpdateTask(Task task) {
        String update = "UPDATE tb_task SET done=?,descr=?,priority=?,dueDate=? WHERE task_id=? AND fk_id_category=?;";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;   
        
        try {
            pst = connection.prepareStatement(update);
            pst.setBoolean(1, task.isDone());
            pst.setString(2, task.getDescr());
            pst.setString(3, task.getPriority());
            pst.setString(4, task.getDueDate());
            pst.setInt(5, task.getId());
            pst.setInt(6, task.getFk_cat_id());           
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na edição: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }
}