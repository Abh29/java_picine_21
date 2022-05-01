package edu.school21.tanks.helpers;

import java.util.Objects;

public class Vect2D {

    private double x;
    private double y;

    public Vect2D(){
        x = 0;
        y = 0;
    }

    public Vect2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+ x +", " + y + " )";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vect2D vect2D = (Vect2D) o;
        return Double.compare(vect2D.x, x) == 0 && Double.compare(vect2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
