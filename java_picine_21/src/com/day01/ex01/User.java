package com.day01.ex01;

import java.util.Objects;

public class User {
    private final int ID;
    private String name;
    private double balance;


    public static User newUser(String name){
        return new User(name);
    }

    public static User newUser(String name, double balance)
    {
        if (balance < 0)
        {
            System.err.println("Balance can not be a negative value!");
            return null;
        }
        return new User(name, balance);
    }

    private User(){
        ID = UserIdsGenerator.getInstance().generateId();
    }

    private User(String name) {
        this();
        setName(name);
        setBalance(0);
    }

    private User(String name, double balance) {
        this(name);
        setBalance(balance);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0)
        {
            System.err.println("Balance can not be a negative value!");
            return;
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + getID() +
                ", name='" + getName() + '\'' +
                ", balance=" + getBalance() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID == user.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, balance);
    }

    /*****************************/


}
