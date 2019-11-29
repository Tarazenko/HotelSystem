package repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool(){
        //private constructor
    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }
    private static HikariDataSource ds;
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hotel?Timezone=true" +
                "&serverTimezone=UTC&user=root&password=1101tar");

        ds = new HikariDataSource(config);
    }
    public  Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
   /* private HikariConfig config = new HikariConfig();
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl("jdbc:mysql://localhost:3306/hotel?Timezone=true" +
                "&serverTimezone=UTC&user=root&password=1101tar");
        HikariDataSource ds = new HikariDataSource(config);
        return ds.getConnection();
    }*/
}
