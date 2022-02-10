package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO {

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
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e);
            return null;
        }
    }

    /* Insert */
    public void InsertUser(User user) {
        String insert = "insert into users (user_name,user_password,user_email) values (?,?,?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, user.getUser());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            JOptionPane.showMessageDialog(null, user.getUser());
            JOptionPane.showMessageDialog(null, user.getPassword());
            JOptionPane.showMessageDialog(null, user.getEmail());

            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir: " + e);
        }
    }

    /* read */
    public String ReadUser(User user) {
        String name = null;
        String read = "select user_name from users where user_name = ?;";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setString(1, user.getUser());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);
            }

            connection.close();
            pst.close();
            rs.close();
            return name;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
            return name;
        }

    }

    public String[] ReadLoginPass(String user) {
        String read = "select user_name,user_password from users where user_name = ?;";
        String[] userD = new String[1];
        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                userD[0] = rs.getString(1);
                userD[1] = rs.getString(2);
            }

            connection.close();
            pst.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
            return null;
        }
        return userD;
    }
    /* update */
 /* delete */
}
