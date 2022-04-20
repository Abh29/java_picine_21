package com.day01.ex04;

import java.util.Arrays;

public class UserArrayList implements UserList {

    private User[] container;
    private int size;

    public UserArrayList(int initialSize)
    {
        if (initialSize < 1)
            throw new IllegalArgumentException("initial array size can not be less than 1");
        container = new User[initialSize];
    }

    public UserArrayList()
    {
        this(10);
    }

    @Override
    public boolean addUser(User user) {
        if (user == null)
            return false;
        if (usersCount() == container.length){
            User[] tmp = new User[(int)(container.length * 1.5)];
            for (int i = 0; i < container.length; i++) {
                tmp[i] = container[i];
            }
            container = tmp;
        }
        container[size++] = user;
        return true;
    }

    @Override
    public User getUserById(int id) {
        for (User u: container) {
            if (u != null && u.getID() == id)
                return u;
        }
        throw new RuntimeException("id does not exists in the arraylist " + id);
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index > size)
            throw new ArrayIndexOutOfBoundsException(index);
        return container[index];
    }

    @Override
    public int usersCount() {
        return size;
    }

    @Override
    public String toString() {
        return "UserArrayList{" +
                "container=" + Arrays.toString(container) +
                ", size=" + size +
                '}';
    }
}
