package edu.school21.chat;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {

    private Properties properties;
    private Connection connection = null;
    private HikariConfig config;

    SimpleDataSource(Properties properties) {
        this.properties = properties;
        config = new HikariConfig();
        loadDriver();
    }

    private void loadDriver() {
        try
        {
            Class.forName(properties.getProperty("db.driver"));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    private void openConnection() {
        try {
            config.setDriverClassName(properties.getProperty("db.driver"));
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.user"));
            config.setPassword(properties.getProperty("db.password"));
            config.addDataSourceProperty( "cachePrepStmts" , "true" );
            DataSource ds = new HikariDataSource(config);
            connection = ds.getConnection();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null)
            openConnection();
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
