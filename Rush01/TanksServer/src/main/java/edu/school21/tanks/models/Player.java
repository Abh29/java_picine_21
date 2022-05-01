package edu.school21.tanks.models;

import edu.school21.tanks.exceptions.DeadPlayerException;
import edu.school21.tanks.exceptions.GameException;
import edu.school21.tanks.helpers.Vect2D;

public class Player {

    private long id;
    private User player;
    private String nick;
    private double hp;
    private Vect2D position;
    private double attackPower;
    private int shots;

    public Player(User player) {
        this.player = player;
    }

    public Player(User player, int hp, Vect2D position) {
        this.player = player;
        this.hp = hp;
        this.position = position;
    }

    public Player(long id, User player, String nick, double hp, Vect2D position, double attackPower, int shots) {
        this.id = id;
        this.player = player;
        this.nick = nick;
        this.hp = hp;
        this.position = position;
        this.attackPower = attackPower;
        this.shots = shots;
    }

    public void takeHit(int hitPoints) throws GameException {
        hp -= hitPoints;
        if (hp <= 0)
            throw new DeadPlayerException("player " + nick + " is dead!");
    }

    public boolean isAlive(){
        return hp > 0;
    }

    public double shoot(){
        shots++;
        return attackPower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public Vect2D getPosition() {
        return position;
    }

    public void setPosition(Vect2D position) {
        this.position = position;
    }

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public double getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(double attackPower) {
        this.attackPower = attackPower;
    }
}
