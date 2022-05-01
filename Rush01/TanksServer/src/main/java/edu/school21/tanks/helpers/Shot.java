package edu.school21.tanks.helpers;


public class Shot {
    private Long shooter_id;
    private Vect2D position;
    private Vect2D direction;
    private double velocity;
    private double damage;


    public Shot(Long shooter_id, Vect2D position, Vect2D direction, double velocity, double damage) {
        this.shooter_id = shooter_id;
        this.position = position;
        this.direction = direction;
        this.velocity = velocity;
        this.damage = damage;
    }

    public Long getShooter_id() {
        return shooter_id;
    }

    public Vect2D getPosition() {
        return position;
    }

    public void setPosition(Vect2D position) {
        this.position = position;
    }

    public Vect2D getDirection() {
        return direction;
    }

    public void setDirection(Vect2D direction) {
        this.direction = direction;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
