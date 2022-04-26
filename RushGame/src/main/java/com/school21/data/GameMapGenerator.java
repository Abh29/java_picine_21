package com.school21.data;

import com.school21.controllers.PathFinder;

import java.util.ArrayList;
import java.util.Random;

public class GameMapGenerator {

    public static Game generate(int size, int foes, int walls) throws GameExceptions{
        if (foes + walls > size * size - 2)
            throw new GameExceptions.ClumpedMapException(foes, walls);
        GameMap gameMap = null;
        while ((gameMap = tryMap(size, foes, walls)) == null);
        return new Game(gameMap);
    }

    private static GameMap tryMap(int size, int foes, int wall){
        GameMap gameMap = new GameMap(size);

        Random random = new Random();

        gameMap.setPlayer(random.nextInt(size), random.nextInt(size));
        gameMap.placePlayer();

        IntegerPair e = gameMap.getEmptyList().remove(random.nextInt(gameMap.getEmptyList().size() - 1));
        gameMap.addExit(e.getX(), e.getY());

        ArrayList<IntegerPair> tested = new ArrayList<>();
        PathFinder pathFinder = new PathFinder(size);

        while (wall > 0){
            IntegerPair tmp = gameMap.getEmptyList().get(random.nextInt(gameMap.getEmptyList().size() - 1));
            if (tested.contains(tmp))
                continue;
            gameMap.addWall(tmp.getX(), tmp.getY());
            pathFinder.setPathMap(gameMap.getMap(), gameMap.EMPTY_CHAR);
            if (pathFinder.aStar(gameMap.getPlayer(), gameMap.getExit()).isEmpty()){
                tested.add(tmp);
                if (tested.size() == gameMap.getEmptyList().size())
                    return null;
                gameMap.setEmpty(tmp.getX(), tmp.getY());
                continue;
            }
            wall--;
        }

        while (foes > 0){
            IntegerPair tmp = gameMap.getEmptyList().get(random.nextInt(gameMap.getEmptyList().size() - 1));
            gameMap.addFoe(tmp.getX(), tmp.getY());
            foes--;
        }
        return gameMap;
    }


}
