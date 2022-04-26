package com.school21;

import com.school21.controllers.GameManager;
import com.school21.data.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello world");


        GameManager gameManager = new GameManager();
        try {
            Game game = gameManager.initiateNewGame(20, 3, 10);
            gameManager.play(game);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
