package com.company;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{

    private final int port;
    private ServerSocket server;
    private List<ServerClientsHandler> clients = new ArrayList<>();
    public static volatile boolean isRunning;

    Server(int port)
    {
        this.port = port;
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
        ServerClientsHandler newClient;

        isRunning = true;
        server = new ServerSocket(port);
        System.out.println("server on");
        while (isRunning)
        {
            newClient = new ServerClientsHandler(server.accept());
            newClient.start();
            clients.add(newClient);
            System.out.println("new client is added !");
        }
        System.out.println("server exited !");
    }

    private void closeConnections()  {
        try {
            for (ServerClientsHandler s: clients) {
                s.interrupt();
            }
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
