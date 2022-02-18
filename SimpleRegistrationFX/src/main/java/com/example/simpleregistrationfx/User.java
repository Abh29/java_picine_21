package com.example.simpleregistrationfx;

public class User {

    private String userName;
    private String email;
    private String passwd;

    public User(String user_name, String email, String passwd) {
        this.userName = user_name;
        this.email = email;
        this.passwd = passwd;
    }


    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPasswd() {
        return this.passwd;
    }
}
