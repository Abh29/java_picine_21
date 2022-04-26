package edu.school21.chat;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private ArrayList<Chatroom> createdRooms;
    private ArrayList<Chatroom> chatrooms;


    public User(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(ArrayList<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public ArrayList<Chatroom> getChatrooms() {
        return chatrooms;
    }

    public void setChatrooms(ArrayList<Chatroom> chatrooms) {
        this.chatrooms = chatrooms;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}
