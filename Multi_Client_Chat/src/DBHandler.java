import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBHandler {

    private final Properties properties;
    private final SimpleDataSource sds;
    private final Connection connection;
    private final Statement statement;

    DBHandler(String propertiesPath)
    {
        this.properties = new Properties();
        try {
            properties.load(new FileReader(propertiesPath));
            sds = new SimpleDataSource(properties);
            connection = sds.getConnection();
            statement = connection.createStatement();

        } catch (Exception e) {
        throw new IllegalArgumentException(e);
    }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void create_users_table() {
        String  sql = "CREATE TABLE users("
                    + "id INT AUTO_INCREMENT,"
                    + "user_name VARCHAR(100),"
                    + "phone varchar (20),"
                    + "password VARCHAR(256),"
                    + "created_at DATETIME,"
                    + "PRIMARY KEY(id))";

        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("table users already exists");
        }
    }

    public void create_chats_table() {
        String  sql = "CREATE TABLE chats("
                + "id INT AUTO_INCREMENT,"
                + "sender_id INT,"
                + "reciever_id INT,"
                + "msg VARCHAR(256),"
                + "viewed tinyint(3) NOT NULL"
                + "sent_at TIMESTAMP ,"
                + "PRIMARY KEY(id))";

        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("table chats already exists");
        }
    }

    public void add_user_to_db(ChatUser user) {
        String  sql = "INSERT INTO users(user_name, phone, password, created_at) values('"
                + user.getUserName() + "','"
                + user.getPhone() + "','"
                + user.getPasswd() + "',"
                + "now())";
        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("could not add user to db");
        }
    }

    public void add_chat_to_db(int sender, int receiver, String msg)
    {
        String  sql = "INSERT INTO chats(sender_id, receiver_id, msg, viewed, sent_at) values('"
                + sender + "','"
                + receiver + "','"
                + msg + "', '0',"
                + "now())";
        try {
            statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("could not add chat to db");
        }
    }


}
