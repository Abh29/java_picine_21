package edu.school21.tanks.models;

import java.util.Objects;

public class User {

    private Long id;
    private String userName;
    private String password;
    private int gamesPlayed;
    private int gamesWon;


    public User(String userName){
        this.userName = userName;
        password = "secret";
        gamesPlayed = 0;
        gamesWon = 0;
    }

    public User(String userName, String password){
        this(userName);
        setPassword(password);
    }

    public User(Long id, String userName, String password) {
        this(userName, password);
        setId(id);
    }

    public User(Long id, String userName, String password, int gamesPlayed, int gamesWon) {
        this(id, userName, password);
        setGamesPlayed(gamesPlayed);
        setGamesWon(gamesWon);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
