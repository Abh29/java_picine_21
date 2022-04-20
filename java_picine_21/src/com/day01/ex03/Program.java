package com.day01.ex03;

public class Program {


    public static void main(String[] args) {

        UserArrayList users = new UserArrayList();

        User u = User.newUser("user0");

        users.addUser(User.newUser("user1"));
        users.addUser(User.newUser("user3"));
        users.addUser(u);
        users.addUser(User.newUser("user5"));
        users.addUser(User.newUser("user2"));
        users.addUser(User.newUser("user9"));
        users.addUser(User.newUser("user4"));

        System.out.println("users count : " + users.usersCount());

        System.out.println("get user by id");
        System.out.println(users.getUserById(1));
        System.out.println("get user by index");
        System.out.println(users.getUserByIndex(0));
        System.out.println("all users");
        System.out.println(users);

    }

}
