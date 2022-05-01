package edu.school21.tanks.server;

import edu.school21.tanks.helpers.Shot;
import edu.school21.tanks.helpers.Vect2D;
import edu.school21.tanks.models.Game;
import edu.school21.tanks.models.Player;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerPlayerInputListener extends Thread{

    private volatile Player player;
    private volatile Game game;
    private volatile BufferedReader playerIn;
    private boolean reversed;

    public ServerPlayerInputListener(Player player, BufferedReader playerIn, Game game, boolean reversed){
        this.player = player;
        this.playerIn = playerIn;
        this.reversed = reversed;
        this.game = game;
    }


    @Override
    public void run() {
        String input;
        while (player.isAlive()){

            try {
                if (playerIn.ready()){
                    input = playerIn.readLine();
                    checkInput(input);
                    input = "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkInput(String input) {
        if (input.equals("exit_game")){
            player.setHp(0);
            game.setUpdated(true);
        }
        else if (input.equals("move_player_right")){
            if (reversed && player.getPosition().getX() > 0)
                player.getPosition().setX(player.getPosition().getX() - 1);
            else if(!reversed && player.getPosition().getY() < 50)
                player.getPosition().setX(player.getPosition().getX() + 1);
            game.setUpdated(true);
        }else if (input.equals("move_player_left")){
            if (reversed && player.getPosition().getX() < 50)
                player.getPosition().setX(player.getPosition().getX() + 1);
            else if(!reversed && player.getPosition().getY() > 0)
                player.getPosition().setX(player.getPosition().getX() - 1);
            game.setUpdated(true);
        }else if (input.equals("shoot!!!")){
            Vect2D direction;
            if (reversed)
                 direction = new Vect2D(0, 1);
            else
                direction = new Vect2D(0, -1);
            Shot shot = new Shot(player.getId(), player.getPosition(), direction, 1, player.getAttackPower());
            player.shoot();
            game.addShot(shot);
            game.setUpdated(true);
        }
    }
}
