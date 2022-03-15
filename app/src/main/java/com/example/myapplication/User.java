package com.example.myapplication;

public class User {
    public String name;
    public String email;
    public String phoneNumber;
    public String location;
    public int score;
    public static int id=0;

    public User(String name, String email, String phoneNumber, String location, int score) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.score = score;
        id++;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getScore() {
        return score;
    }
    public static int getId(){
        return id;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
