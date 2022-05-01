package edu.school21.tanks.models;

import edu.school21.tanks.helpers.Shot;
import edu.school21.tanks.helpers.Vect2D;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Long id;
    private Player player1;
    private Player player2;
    private double initialHp;
    private List<Shot> shots;

    public Game(Player player1, Player player2, double initialHp) {
        this.player1 = player1;
        this.player2 = player2;
        this.initialHp = initialHp;
        shots = new ArrayList<>();
    }

    public Game(Long id, Player player1, Player player2, double initialHp) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.initialHp = initialHp;
        shots = new ArrayList<>();
    }

    public void setPlayersPositions(Vect2D pos1, Vect2D pos2){
        player1.setPosition(pos1);
        player2.setPosition(pos2);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public double getInitialHp() {
        return initialHp;
    }

    public void setInitialHp(double initialHp) {
        this.initialHp = initialHp;
    }

    public List<Shot> getShots() {
        return shots;
    }

    public void setShots(List<Shot> shots) {
        this.shots = shots;
    }
}
