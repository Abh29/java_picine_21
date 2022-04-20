package com.day01.ex00;

import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID identifier;
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
        identifier = UUID.randomUUID();
    }

    private User(String name) {
        this();
        this.name = name;
        this.balance = 0;
    }

    private User(String name, double balance) {
        this(name);
        this.balance = balance;
    }

    public UUID getIdentifier() {
        return identifier;
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
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identifier.equals(user.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, balance);
    }
}
