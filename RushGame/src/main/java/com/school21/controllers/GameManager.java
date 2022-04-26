package com.school21.controllers;

import com.school21.data.Game;
import com.school21.data.GameMapGenerator;

public class GameManager implements IGameManager{

    @Override
    public Game initiateNewGame(int size, int enemyCount, int wallCount) throws Exception {

        Game game = GameMapGenerator.generate(size, enemyCount, wallCount);
        return game;
    }

    @Override
    public void play(Game game) {
        System.out.println("playing the game");
    }
}
