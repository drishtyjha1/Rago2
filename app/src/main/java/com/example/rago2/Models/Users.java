package com.example.rago2.Models;

public class Users {
    String profilepic;
    String username;
    String password;
    String email;
    String userId;

    public Users(String profilepic, String username, String password, String email, String userId) {
        this.profilepic = profilepic;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    public Users(){}

    //Signup Constructor

    public Users( String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }



    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}