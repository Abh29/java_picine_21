package com.day01.ex03;

public interface UserList {

    boolean addUser(User user);
    User getUserById(int id);
    User getUserByIndex(int index);
    int usersCount();
}
