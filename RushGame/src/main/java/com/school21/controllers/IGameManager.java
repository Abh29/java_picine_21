package com.school21.controllers;

import com.school21.data.Game;

public interface IGameManager {

    Game    initiateNewGame(int size, int enemyCount, int wallCount) throws Exception;
    void    play(Game game);
}
