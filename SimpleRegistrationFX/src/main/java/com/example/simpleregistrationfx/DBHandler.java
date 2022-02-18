package com.example.simpleregistrationfx;

import java.io.FileReader;
import java.security.MessageDigest;
import java.sql.*;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DBHandler {

    private final Properties properties;
    private final SimpleDataSource sds;
    private final Connection connection;

    DBHandler(String propertiesPath) {
        this.properties = new Properties();
        try {
            properties.load(new FileReader(propertiesPath));
            sds = new SimpleDataSource(properties);
            connection = sds.getConnection();

        } catch (Exception e) {
        throw new IllegalArgumentException(e);
    }
    }

    public Connection getConnection() {
        return connection;
    }

    public void create_users_table() {
        String  sql = "CREATE TABLE users("
                    + "id INT AUTO_INCREMENT,"
                    + "user_name VARCHAR(100),"
                    + "email varchar (100) UNIQUE ,"
                    + "password VARCHAR(256),"
                    + "created_at DATETIME,"
                    + "PRIMARY KEY(id))";
        System.out.println(sql);
        try {
            connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("table users already exists");
        }
    }

    public boolean add_user_to_db(User user) throws SQLException {
        String  sql = "INSERT INTO users(user_name, email, password, created_at) values(?, ?, ?, now())";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPasswd());
        try {
           statement.execute();
        } catch (SQLException e) {
            System.out.println("could not add user to db");
            return false;
        }
        return true;
    }

    public boolean checkIfEmailExists(String email) throws SQLException {
        String sql = "select * from users where email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        ResultSet rs = statement.executeQuery();
        return (!rs.next());
    }

    public String encrpytPasswd(String password)
    {
        try
        {
            MessageDigest mg = MessageDigest.getInstance("SHA-256");
            byte[] digestedData = mg.digest(password.getBytes(UTF_8));
            return new String(digestedData);
        }
        catch (Exception e)
        {
            System.out.println("could not encrypt password");
            return password;
        }
    }

}
