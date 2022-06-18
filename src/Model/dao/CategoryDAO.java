package Model.dao;

import Model.bean.Category;
import Model.bean.Task;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryDAO {

    UserPass.dbUser u = new UserPass.dbUser();
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/todolist?useTimezone=true&serverTimezone=UTC";
    private String user = "" + u.getUsername() + "";
    private String password = "" + u.getPass() + "";

    /* CONNECT */
    public Connection Connect() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            return null;
        }
    }

    /* insere uma nova categoria */
    public void saveCategory(Category cat) {
        String insert = "insert into Category (name,fk_User) values (?,?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, cat.getCat_name());
            pst.setInt(2, cat.getFk_User());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: " + e);
        }
    }

    /* EXIBE TODAS AS CATEGORIAS */
    public void ReadCat(JTable tb_category,Category cat) {
        DefaultTableModel dtm = (DefaultTableModel) tb_category.getModel();
        String read = "SELECT name FROM Category WHERE fk_User=? ORDER BY idCategory";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            ResultSet rs = pst.executeQuery();
            dtm.setRowCount(0);

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getString(1)});
            }

            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }

    /* LE O PRIMEIRO ID DA CATEGORIA */
    public void ReadFirstCatID(Category cat, Task task) {
        String read = "SELECT idCategory FROM Category  WHERE fk_User=? ORDER BY idCategory Limit 1";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                cat.setCat_id(rs.getInt(1));
                task.setFk_cat_id(rs.getInt(1));
            }

            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }

    /* LE O ID DA CATEGORIA */
    public void ReadCatIDWhereName(Category cat) {
        String read = "SELECT idCategory,name FROM Category WHERE fk_User=? AND name=?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setInt(1, cat.getFk_User());
            pst.setString(2, cat.getCat_name());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                cat.setCat_id(rs.getInt(1));
            }

            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
        }
    }

    /* Edita a categoria */
    public void UpdateCat(Category cat) {
        String update = "UPDATE Category SET name=? WHERE idCategory=?";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setString(1, cat.getCat_name());
            pst.setInt(2, cat.getCat_id());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na edição: " + e);
        }
    }

    /* DELETE A CATEGORIA PELO ID  */
    public void deleteCat(Category cat) {
        String delete = "DELETE FROM Category WHERE idCategory=?";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(delete);
            pst.setInt(1, cat.getCat_id());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no delete: " + e);
        }
    }
}
