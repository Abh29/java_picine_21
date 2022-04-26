package com.school21.data;

public class GameExceptions extends Exception{

    GameExceptions(String str){
        super(str);
    }

    public static class WrongMapSizeException extends Exception{
        WrongMapSizeException(int size){
            super("The wrong size was provided " + size);
        }
    }

    public static class ClumpedMapException extends GameExceptions{

        ClumpedMapException(int foes, int walls) {
            super("The map is too full with " + foes + " foes and " + walls + " wals try other properties");
        }
    }

    public static class OccupiedCellException extends GameExceptions{
        OccupiedCellException(int x, int y){
            super("trying to add element to (" + x + ", " + y +"), Cell is not free!");
        }
    }

    public static class GameWinException extends GameExceptions{
        GameWinException(){
            super("congrat you have won!");
        }
    }

    public static class GameLostException extends GameExceptions{
        GameLostException(){
            super("game over!");
        }
    }
}
