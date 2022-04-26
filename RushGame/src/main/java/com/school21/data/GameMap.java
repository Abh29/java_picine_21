package com.school21.data;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private char[][] map;
    private List<IntegerPair> emptyList;
    private List<IntegerPair> foes;
    private IntegerPair player;
    private IntegerPair exit;

    public static final char EMPTY_CHAR = 0;
    public static final char FOE_CHAR = 'x';
    public static final char WALL_CHAR = '#';
    public static final char PLAYER_CHAR = '0';
    public static final char EXIT_CHAR = 'O';

    protected GameMap(int size){
        map = new char[size][size];
        emptyList = new ArrayList<>(size * size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptyList.add(new IntegerPair(i, j));
            }
        }
        foes = new ArrayList<>();
    }


    protected boolean isPlayerSet(){
        return (this.player != null);
    }

    protected IntegerPair getPlayer() {
        return player;
    }

    public IntegerPair getExit() {
        return exit;
    }

    protected boolean isEmpty(int x, int y){
        return (map[x][y] == EMPTY_CHAR);
    }

    protected boolean isPlayer(int x, int y){
        return (map[x][y] == PLAYER_CHAR);
    }

    protected boolean isWall(int x, int y){
        return (map[x][y] == WALL_CHAR);
    }

    protected boolean isFoe(int x, int y){
        return (map[x][y] == FOE_CHAR);
    }

    protected boolean isExit(int x, int y){
        return (map[x][y] == EXIT_CHAR);
    }

    protected void setPlayer(int x, int y) {
        this.player = new IntegerPair(x, y);
        emptyList.remove(new IntegerPair(x, y));
    }

    protected void addWall(int x, int y){
        map[x][y] = WALL_CHAR;
        emptyList.remove(new IntegerPair(x, y));
    }

    protected void addExit(int x, int y){
        map[x][y] = EXIT_CHAR;
        emptyList.remove(new IntegerPair(x, y));
    }

    protected void updateMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == FOE_CHAR || map[i][j] == PLAYER_CHAR)
                    map[i][j] = EMPTY_CHAR;
            }
        }
        placePlayer();
        placeFoes();
    }

    protected void placePlayer(){
        map[player.getX()][player.getY()] = PLAYER_CHAR;
    }

    protected void placeFoes(){
        for (IntegerPair p : foes) {
            map[p.getX()][p.getY()] = FOE_CHAR;
        }
    }

    protected void addFoe(int x, int y){
        IntegerPair f = new IntegerPair(x, y);
        foes.add(f);
        emptyList.remove(f);
    }

    public char[][] getMap() {
        return map;
    }

    public List<IntegerPair> getFoes() {
        return foes;
    }

    public List<IntegerPair> getEmptyList() {
        return emptyList;
    }

    public void setEmpty(int x, int y){
        IntegerPair e = new IntegerPair(x, y);
        if (emptyList.contains(e))
            return;
        if (foes.contains(e))
            foes.remove(foes.indexOf(e));
        emptyList.add(e);
        map[e.getX()][e.getY()] = EMPTY_CHAR;
    }


}
