package com.school21.data;

public class Game {
    private GameMap gameMap;

    protected Game(GameMap gameMap){
        this.gameMap = gameMap;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
