package dao;

import connections.ConnectionFactory;
import entities.Category;
import entities.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryDAO {

    /* insere uma nova categoria */
    public void saveCategory(Category cat) {
        String insert = "insert into tb_category (cat_name,fk_id_user) values (?,?)";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(insert);
            pst.setString(1, cat.getCat_name());
            pst.setInt(2, cat.getFk_User());
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }

    /* EXIBE TODAS AS CATEGORIAS */
    public void ReadCat(JTable tb_category,Category cat) {
        DefaultTableModel dtm = (DefaultTableModel) tb_category.getModel();
        String read = "SELECT cat_name FROM tb_category WHERE fk_id_user=? ORDER BY cat_id";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;     
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            rs = pst.executeQuery();
            dtm.setRowCount(0);

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString(1)});
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
    }

    /* LE O PRIMEIRO ID DA CATEGORIA */
    public void ReadFirstCatID(Category cat, Task task) {
        String read = "SELECT cat_id FROM tb_category WHERE fk_id_user=? ORDER BY cat_id Limit 1";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;     
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            rs = pst.executeQuery();

            while (rs.next()) {
                cat.setCat_id(rs.getInt(1));
                task.setFk_cat_id(rs.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
        
    }

    /* LE O ID DA CATEGORIA */
    public void ReadCatIDWhereName(Category cat) {
        String read = "SELECT cat_id,cat_name FROM tb_category WHERE fk_id_user=? AND cat_name=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;     
        
        try {
            pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            pst.setString(2, cat.getCat_name());
            rs = pst.executeQuery();

            while (rs.next()) {
                cat.setCat_id(rs.getInt(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
    }

    /* Edita a categoria */
    public void UpdateCat(Category cat) {
        String update = "UPDATE tb_category SET cat_name=? WHERE cat_id=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null; 
        
        try {
            pst = connection.prepareStatement(update);
            pst.setString(1, cat.getCat_name());
            pst.setInt(2, cat.getCat_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na edição: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }

    /* DELETE A CATEGORIA PELO ID  */
    public void deleteCat(Category cat) {
        String delete = "DELETE FROM tb_category WHERE cat_id=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        try {
            pst = connection.prepareStatement(delete);
            pst.setInt(1, cat.getCat_id());
            pst.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no delete: " + e);
        
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }
}
