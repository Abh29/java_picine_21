package com.day01.ex02;

public class UserIdsGenerator {
    private static int idCounter = 0;
    private static UserIdsGenerator instance = null;


    private UserIdsGenerator(){};

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public int generateId(){
        return ++idCounter;
    }

    public static int getLastID() {
        return idCounter;
    }
}
