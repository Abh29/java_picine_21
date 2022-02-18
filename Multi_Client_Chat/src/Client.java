import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket client;
    private PrintWriter out;
    private BufferedReader in;

    public void start(String ip, int port) throws IOException {
        client = new Socket(ip, port);
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public void sendMsg(String msg) throws IOException {
        String resp = null;
          out.println(msg);

        resp = in.readLine();
        if (!resp.isEmpty())
            System.out.println(resp);
    }

    public void sendIdentifyMeMsg() throws IOException{
        String resp;

        while (true)
        {
            out.println("identify_me");
            resp = in.readLine();
            if (!resp.isEmpty() && resp.equals("identify_me_received"))
                break;
        }
        System.out.println(in.readLine());
    }

    public void checkUnreadMsg() {
        String msg;
        try
        {
            msg = in.readLine();
            System.out.println("this is msg " +  msg);
            while (!msg.equals("##end_msgs"))
            {
                System.out.println(msg);
                msg = in.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("Error when reading msgs");
        }
    }

    public void identify(Scanner scanner) {
        String msg;
        String resp;

        while (true)
        {
            msg = scanner.nextLine();
            if (msg.equals("exit"))
                break;
            try {
                out.println(msg);
                resp = in.readLine();
                System.out.println(resp);
                if (resp.equals("connected ."))
                    return;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void close() {
        try {
            in.close();
            out.close();
            client.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
