package edu.school21.sockets.server;


import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread{

    private final int port;
    private ServerSocket server;
    private PrintWriter clientOut;
    private BufferedReader clientIn;


    public static volatile boolean isRunning;
    public UsersService usersService;


    public Server(int port)
    {
        this.port = port;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public void run() {
        try {
            startConnection(port);
        }
        catch (Exception e)
        {
            System.out.println("server closing !");
        }
    }

    private void startConnection(int port) throws IOException {
        isRunning = true;
        server = new ServerSocket(port);
        Socket clientSocket = server.accept();
        clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
        clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("client connected!");
        clientOut.println("Hello from Server!");
        while (isRunning)
        {
            int count = 0;
            String msg = "";

            try
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
                    msg = clientIn.readLine();
                    clientOut.print("msg received : ");
                    System.out.println(msg);

                    if(msg.equals("signUp"))
                        signUpNewUser();
                    isRunning = false;
            }
            catch (Exception e)
            {
                System.out.println("connection closed !");
                isRunning = false;
            }
            finally {
                clientOut.close();
                clientIn.close();
                closeConnections();
            }

        }
        System.out.println("server exited !");
    }


    private void signUpNewUser() throws IOException {
        clientOut.println("Enter username:");
        String userName = clientIn.readLine();
        clientOut.println("Enter password:");
        String password = clientIn.readLine();
        User user = usersService.signUp(userName, password);
        if (user != null)
            clientOut.println("Successful!");
        else
            clientOut.println("Fail, choose another username");
    }



    private void closeConnections()  {
        try{
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void interrupt() {
        closeConnections();
        super.interrupt();
    }
}