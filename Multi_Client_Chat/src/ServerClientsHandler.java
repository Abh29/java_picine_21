import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerClientsHandler extends Thread{
    private final Socket clientSocket;
    private PrintWriter clientOut;
    private BufferedReader clientIn;
    private boolean isRunning;
    private Statement statement;
    private ChatUser clientChatter;

    ServerClientsHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
        clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        isRunning = true;
        statement = Server_main.db.getStatement();
    }

    @Override
    public void run() {

        listenToIdentifyMe();
        clientChatter = first_connection();
        sendUnreadMsg(clientChatter);
        chatWithClient(clientChatter);
    }

    private void listenToIdentifyMe() {
        String msg;
        try {
            while(true)
            {
                msg = clientIn.readLine();
                if (msg.equals("identify_me"))
                {
                    clientOut.println("identify_me_received");
                    return;
                }

            }
        }catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    private ChatUser first_connection() {
        ChatUser out = null;
        String msg;

        try {
            while (true) {
                clientOut.println("please type : signin or signup");
                msg = clientIn.readLine();
                if (msg.equals("signup"))
                    out = createNewChatter();
                else if (msg.equals("signin"))
                    out = identifyChatter();
                if (out != null)
                    break;
            }
        }catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        clientOut.println("connected .");
        return out;
    }

    private void   sendUnreadMsg(ChatUser user){
        ResultSet rs;
        ResultSet rs2;

        try {
            rs = statement.executeQuery("select * from chats where receiver_id = '" + user.getId() + "' and viewed = '0'");

            while (rs.next())
            {
                rs2 = statement.executeQuery("select * from users where id = '" + rs.getString("sender_id") + "'");
                if (rs2.next())
                {
                    clientOut.println(rs2.getString("user_name") + "  : " + rs.getString("msg"));
                    statement.executeQuery("UPDATE chats SET viewed = '1' WHERE id = '" + rs.getString("id") + "'");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        clientOut.println("##end_msgs");
    }

    private void chatWithClient(ChatUser user) {
        String msg;
        int     count = 0;

        try
        {
            while (isRunning)
            {
                while (!clientIn.ready() && isRunning)
                {
                    Thread.sleep(10);
                    count++;
                    if (count >= 30000) // 5 min without connection
                        break;
                }
                if (!isRunning)
                    break;
                count = 0;
                msg = clientIn.readLine();
                if (msg.equals("##send_msg_to"))
                    addMsgToChat(user);
                else
                {
                    clientOut.println("msg received.");
                    System.out.println(user.getUserName() + "   -   " + msg);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("connection closed !");
            isRunning = false;
        }
        finally {
            closeConnection();
        }
    }

    private void addMsgToChat(ChatUser user){
        String msg;
        ResultSet rs;
        int id;

        System.out.println("adding a new chat to db");
        try {
            while(true)
            {
                clientOut.println("put receiver_name (20 char max) : ");
                msg = clientIn.readLine();
                if (msg.length() > 20)
                    continue;
                rs = statement.executeQuery("select * from users where user_name = '" + msg + "'");
                if (!rs.next())
                    continue;

                id = rs.getInt("id");
                break ;
            }
            while(true)
            {
                clientOut.println("put msg (255 char max) : ");
                msg = clientIn.readLine();
                if (msg.length() > 255)
                    continue;
                msg = msg.replace(' ', '_');
               Server_main.db.add_chat_to_db(user.getId(), id, msg);
               clientOut.println("chat saved to db");
               break;
            }

        }catch (Exception e)
        {
            throw new IllegalArgumentException(e);
        }
    }

    private void listenUntilString(String sendIfNotMatch, String expected) throws Exception {
        String msg;
        while (true)
        {
            msg = clientIn.readLine();
            if (msg.equals(expected))
                break;
            clientOut.println(sendIfNotMatch);
        }
    }

    private ChatUser createNewChatter() throws Exception{
        ChatUser    out;
        String      user_name;
        String      phone;
        String      password;
        ResultSet   rs;

        while (true)
        {
            clientOut.println("user_name (20 char max):");
            user_name = clientIn.readLine();
            if (user_name.length() > 20)
                continue;
            if (user_name.equals("cancel"))
                return null;
            rs = statement.executeQuery("select * from users where user_name = '" + user_name + "'");
            if (rs.next())
            {
                clientOut.println("this user_name is used before. change it or type 'cancel' to start over");
                continue;
            }
            break;
        }
            clientOut.println("password :");
            password = clientIn.readLine();
            if (password.equals("cancel"))
                return null;

            clientOut.println("phone :");
            phone = clientIn.readLine();
            if (phone.equals("cancel"))
                return null;

            out = new ChatUser(user_name, phone, password);
            Server_main.db.add_user_to_db(out);

            rs = statement.executeQuery("select id where user_name = '" + out.getUserName() + "' and password = '" + out.getPasswd() + "'");
            if (rs.next())
                out.setId(rs.getInt("id"));
        return out;
    }

    private ChatUser identifyChatter() throws Exception{
        ChatUser    out;
        String      user_name;
        String      password;
        String      phone;
        int         id;
        ResultSet   rs;

        while (true)
        {
            clientOut.println("user_name (20 char max):");
            user_name = clientIn.readLine();
            System.out.println("this is usesr_name" + user_name);
            if (user_name.length() > 20)
                continue;
            if (user_name.equals("cancel"))
                return null;

            clientOut.println("password :");
            password = clientIn.readLine();
            if (password.equals("cancel"))
                return null;
            rs = statement.executeQuery("select * from users where user_name = '" + user_name + "' and password = '" + password + "'");
            if (rs.next())
                break;
            clientOut.println("no user found!, make sure that the password and the uesr name are correct.");
        }

        id = rs.getInt("id");
        user_name = rs.getString("user_name");
        phone = rs.getString("phone");
        password = rs.getString("password");


        out = new ChatUser(id, user_name, phone, password);

        return out;
    }

    private void closeConnection() {
        clientOut.println("Server out.");
        try
        {
            clientOut.close();
            clientIn.close();
            clientSocket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        isRunning = false;
        closeConnection();
        super.interrupt();
    }
}
