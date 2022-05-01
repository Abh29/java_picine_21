package edu.school21.tanks.server;

import edu.school21.tanks.helpers.Vect2D;
import edu.school21.tanks.models.Game;
import edu.school21.tanks.models.Player;
import edu.school21.tanks.services.GamesService;
import edu.school21.tanks.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerGameHandler extends Thread{

    private final Socket playerSocket1;
    private final Socket playerSocket2;
    private UsersService usersService;
    private GamesService gamesService;
    private PrintWriter clientOut1;
    private PrintWriter clientOut2;
    private BufferedReader clientIn1;
    private BufferedReader clientIn2;
    private Player player1;
    private Player player2;
    private Game game;
    private int height;
    private int width;


    public ServerGameHandler(Socket playerSocket1, Socket playerSocket2, Player player1, Player player2) throws IOException {
        this.playerSocket1 = playerSocket1;
        this.playerSocket2 = playerSocket2;
        this.player1 = player1;
        this.player2 = player2;
        clientOut1 = new PrintWriter(playerSocket1.getOutputStream());
        clientOut2 = new PrintWriter(playerSocket2.getOutputStream());
        clientIn1 = new BufferedReader(new InputStreamReader(playerSocket1.getInputStream()));
        clientIn2 = new BufferedReader(new InputStreamReader(playerSocket2.getInputStream()));
        height = 100;
        width = 50;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void setGamesService(GamesService gamesService){
        this.gamesService =gamesService;
    }

    public void setMapSize(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    public void run() {
        init();
        play();
        showStats();
    }

    private void init() {
        String request = "init;map_height="+ height + ";map_width=" + width;
        request += ";player1=" + player1.getId() + ";player1_x=1;player1_y=25;";
        request += "player2=" + player2.getId() + ";player2_x=99;player2_y=25;";
        request += "init_hp=100;attack_power=5;";

        clientOut1.println(request);
        clientOut2.println(request);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player1.setPosition(new Vect2D(1, 25));
        player1.setHp(100);
        player1.setAttackPower(5);
        player1.setShots(0);
        player2.setPosition(new Vect2D(99, 25));
        player2.setHp(100);
        player2.setAttackPower(5);
        player2.setShots(0);

    }

    private void showStats() {
    }

    private void play() {
        

    }
}
