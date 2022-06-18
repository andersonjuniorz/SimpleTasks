package Model.dao;

import Model.bean.User;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserDAO {

    UserPass.dbUser u = new UserPass.dbUser();
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/todolist?useTimezone=true&serverTimezone=UTC";
    private String user = "" + u.getUsername()+ "";
    private String password = "" + u.getPass() + "";

    /* CONNECT */
    public Connection Connect() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e);
            return null;
        }
    }

    /* CRIAR NOVO USUARIO */
    public void InsertUser(User user) {
        String insert = "insert into User (username,passw,email,recoveryCode) values (?,?,?,?)";

        try {
            Connection conn = Connect();
            PreparedStatement pst = conn.prepareStatement(insert);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPass());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getRecoveryCode());
            pst.executeUpdate();

            conn.close();
            pst.close();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir: " + e);
            System.out.println(e);
        }
    }

    /* read */
    public String ReadUser(User user) {
        String name = null;
        String read = "select username from User where username = ?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setString(1, user.getUsername());
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

    /* PESQUISA USUARIO PELO USERNAME */
    public User selectUser(String username) {
   
        User user = new User();
        String read = "select idUser, username, passw from User where username=?";

        try {
            Connection connection = Connect();
            PreparedStatement pst = connection.prepareStatement(read);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                //           id,           username,        password
                user.setUser(rs.getInt(1), rs.getString(2), rs.getString(3));                
            }
            
            connection.close();
            pst.close();
            rs.close();
            
        } catch (SQLException e) {
            System.out.print("Erro: " + e);
        }
        return user;
    }
}
