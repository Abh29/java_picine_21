package com.day01.ex01;

public class Program {
    public static void main(String[] args) {
        User john = User.newUser("John", 2500);
        User mark = User.newUser("Mark", 1200);

        System.out.println(john);
        System.out.println(mark);

        System.out.println("last id generated " + UserIdsGenerator.getLastID());
    }
}
