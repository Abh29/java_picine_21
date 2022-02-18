import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server_main {

    public volatile static List<ChatUser> allChatters = new ArrayList<>();
    public volatile static DBHandler db;

    public static void main(String[] args) {

        db = new DBHandler("resources\\application.properties");
        db.create_users_table();
        db.create_chats_table();


        Scanner in = new Scanner(System.in);
        Server server = new Server(6655);
        server.start();
        while (true) {
            if (in.nextLine().equals("exit")) {
                server.interrupt();
                break;
            }
        }
    }
}
