package com.example.microhousingsystem;

public class User {
    public String id;
    public String username;
    public String password;
    public String fullname;
    public String userType;


    public User() {
    }
    public User(String id, String username, String password) {
        this.id = id;
        this.username =username;
        this.password = password;

    }

    public User(String id, String username, String password, String fullname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }
    //auth
    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
