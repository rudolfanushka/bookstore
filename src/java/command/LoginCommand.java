package command;

import pool.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginCommand implements ActionCommand {

    private final Connection con;
    private final ConnectionPool pool;

    public LoginCommand() throws InterruptedException, IllegalAccessException, InstantiationException {
        this.pool = ConnectionPool.getInstance();
        this.con = pool.getConnection();
    }

    public static final String SQL_CHECK_LOGIN_PASSWORD = "SELECT * FROM user WHERE login = ? AND password = ?";

    private String page;


    public String execute(HttpServletRequest request) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        PreparedStatement prst;

        try {
            prst = con.prepareStatement(SQL_CHECK_LOGIN_PASSWORD);
            prst.setString(1, login);
            prst.setString(2, password);
            ResultSet rs = prst.executeQuery();

            if (rs.next()) {
                request.getSession(true).setAttribute("userId", rs.getInt("userId"));
                page = new AllBooksCommand().execute(request);
            }
        } catch (SQLException e) {
            //LOG.debug("Unknown" + e);
        }

        return page;

    }
}