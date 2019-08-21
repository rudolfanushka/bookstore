package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static ReentrantLock lock;
    private BlockingQueue <Connection> connections;
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/bookstore";
    private final static String DATABASE_LOGIN = "root";
    private final static String DATABASE_PASSWORD = "";
    private final static int CONNECTION_QUANTITY = Integer.parseInt("7");
    private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    private ConnectionPool() throws InterruptedException, IllegalAccessException, InstantiationException {
        Connection con;
        try {
            connections = new ArrayBlockingQueue<>(CONNECTION_QUANTITY);
            Properties prop = new Properties();
            prop.put("user", DATABASE_LOGIN);
            prop.put("password", DATABASE_PASSWORD);
            prop.put("autoReconnect", "true");
            prop.put("characterEncoding", "UTF-8");
            prop.put("useUnicode", "true");
            Class.forName(DATABASE_DRIVER).newInstance();

            for (int i = 0; i < CONNECTION_QUANTITY; i++) {
                con = DriverManager.getConnection(DATABASE_URL, prop);
                if (con != null) {
                    connections.put(con);
                }
            }
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        }
    }

    public static ConnectionPool getInstance() throws InterruptedException, IllegalAccessException, InstantiationException {

        lock = new ReentrantLock();
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException{

        Connection con = null;
        con = connections.take();
        if (con == null) {
        }
        return con;
    }

    public void returnConnection(Connection con) {
        try {
            if (con != null) {
                connections.put(con);
            }
        } catch (InterruptedException e) {
        }
    }

    public static void releaseConnectionPool() {
        if (instance != null) {
            lock.lock();
            try {
                if (instance != null) {
                    instance.connections.stream().filter((con) -> (con != null)).forEach((con) -> {
                        try {
                            con.close();
                        } catch (SQLException e) {
                        }
                    });
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

