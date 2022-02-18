import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws Exception{

        DBHandler db = new DBHandler("resources\\application.properties");

        db.create_users_table();
        db.create_chats_table();

        System.out.println("fine");
    }
}
