package dao;

import data.User;
import pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO{

    public static final String SQL_ADD_USER = "INSERT INTO USER (NAME, LOGIN, PASSWORD) VALUES (?,?,?)";


    public boolean addNewUser(User user) {

        Connection con = null;
        ConnectionPool pool = null;
        PreparedStatement ps = null;
        try {
            pool = ConnectionPool.getInstance();

            String name = user.getName();
            String login = user.getLogin();
            String password = user.getPassword();

            con = pool.getConnection();

            ps = con.prepareStatement(SQL_ADD_USER);
            ps.setString(1, name);
            ps.setString(2, login);
            ps.setString(3, password);
            ps.executeUpdate();
            return true;

        } catch (InterruptedException | IllegalAccessException | InstantiationException e) {
        } catch (SQLException e) {
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                }
            }
            pool.returnConnection(con);
        }
        return false;
    }
}
