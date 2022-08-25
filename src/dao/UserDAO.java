package dao;

import connections.ConnectionFactory;
import entities.User;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UserDAO {

    /* CRIAR NOVO USUARIO */
    public void InsertUser(User user) {
        String insert = "insert into tb_user (username,passw,email) values (?,?,?)";
        
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(insert);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPass());
            pst.setString(3, user.getEmail());            
            pst.executeUpdate();

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir: " + e);
            System.out.println(e);
            
        }finally{
            ConnectionFactory.closeConnection(connection, pst);
        }
    }

    /* READ USERNAME */
    public String ReadUser(User user) {
        String name = null;             
        String read = "select username from tb_user where username = ?";
  
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = connection.prepareStatement(read);
            pst.setString(1, user.getUsername());
            rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString(1);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura: " + e);
            
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
        return name;
    }

    /* PESQUISA USUARIO PELO USERNAME */
    public User selectUser(String username) {
   
        User user = new User();
        String read = "select user_id, username, passw from tb_user where username=?";

        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            pst = connection.prepareStatement(read);
            pst.setString(1, username);            
            rs = pst.executeQuery();

            while (rs.next()) {   
                
                user.setUser(
                        rs.getInt(1),     //id
                        rs.getString(2), //username
                        rs.getString(3) //password
                );                
            }
            
        } catch (SQLException e) {
            System.out.print("Erro: " + e);
            
        }finally{
            ConnectionFactory.closeConnection(connection, pst, rs);
        }
        return user;
    }
}
