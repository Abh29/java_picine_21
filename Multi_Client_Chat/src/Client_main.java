import java.io.IOException;
import java.util.Scanner;

public class Client_main {

    static final String LOCALHOST = "127.0.0.1";
    public static void main(String[] args) throws Exception{
	    Client client = new Client();
        Scanner in = new Scanner(System.in);
        String msg;

        client.start(LOCALHOST, 6655);
        client.sendIdentifyMeMsg();
        client.identify(in);
        client.checkUnreadMsg();

        System.out.println("put send_msg to send a new msg :");
        while (true)
        {
            msg = in.nextLine();
            if (msg.equals("exit"))
                break;
            if (msg.equals("send_msg"))
                msg = "##send_msg_to";
            try {
                client.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        client.close();
    }
}
