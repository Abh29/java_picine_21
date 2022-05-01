package edu.school21.tanks.server;


import edu.school21.tanks.models.Player;
import edu.school21.tanks.services.GamesService;
import edu.school21.tanks.services.UsersService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server extends Thread{
    private final int PORT;
    private final int MAX_USERS;
    private ServerSocket server;
    private List<ServerClientHandler> clients = new ArrayList<>();
    private GamesService gamesService;
    private UsersService usersService;
    public static volatile boolean isRunning;

    public Server(int port, int MAX_USERS) {
        this.PORT = port;
        this.MAX_USERS = MAX_USERS;
    }

    @Override
    public void run() {
        try {
            startConnection(PORT);
        }
        catch (Exception e)
        {
            System.out.println("server closing !");
        }
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void setGamesService(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    private void startConnection(int port) throws IOException {
        ServerClientHandler newClient;

        isRunning = true;
        server = new ServerSocket(port);
        System.out.println("server on");

        Player[] players = new Player[MAX_USERS];
        Socket[] sockets = new Socket[MAX_USERS];

        int i = 0;
        while (isRunning)
        {
            sockets[i] = server.accept();
            newClient = new ServerClientHandler(sockets[i], players[i++]);
            newClient.setUsersService(usersService);
            newClient.start();
            clients.add(newClient);
            System.out.println("new client is added !");
            if (clients.size() >= MAX_USERS)
                break;
        }

        if (isRunning){

        }
        System.out.println("server exited !");
    }

    private void closeConnections()  {
        try {
            for (ServerClientHandler s: clients) {
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