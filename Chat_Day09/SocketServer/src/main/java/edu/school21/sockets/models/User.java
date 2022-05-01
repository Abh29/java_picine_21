package edu.school21.sockets.models;

import java.util.Objects;

public class User {

    private final Long ID;
    private String userName;
    private String password;

    public User(Long id, String userName){
        this.ID = id;
        this.userName = userName;
        password = "secret";
    }

    public User(Long id, String userName, String password) {
        this.ID = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getID() {
        return ID;
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

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ID.equals(user.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
