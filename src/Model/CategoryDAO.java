package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryDAO {

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
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        } finally {
            return connection;
        }
    }

    /* Insert */
    public void insertCat(Category cat) {
        String insert = "insert into category (cat_name) values (?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, cat.getCat_name());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de inserção: " + e);
        }
    }

    /* SELECT */
    public void ReadCat(JTable tb_category) {
        DefaultTableModel dtm = (DefaultTableModel) tb_category.getModel();
        String read = "select cat_name from category ORDER BY cat_id";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
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

    /* SELECT ID WHERE CATEGORY_NAME IS '?' */
    public void ReadCatID(Category cat) {
        String read = "select cat_id from category where cat_name = ?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setString(1, cat.getCat_name());
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

    /* DELETE */
    public void deleteCat(Category cat) {
        String delete = "delete from category where cat_id=?";

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
