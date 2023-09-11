package com.example.va3prog2.models;
import java.util.ArrayList;

public class User {
    private String fullName;
    private String password;
    private String nickname;
    private ArrayList<String> gifts; // ArrayList of gift strings

    // Constructor
    public User(String fullName, String password, String nickname) {
        this.fullName = fullName;
        this.password = password;
        this.nickname = nickname;
        this.gifts = new ArrayList<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    // Add a gift to the user's gift list
    public void addGift(Gift gift) {
        gifts.add(String.valueOf(gift));
    }

    // Get the user's gift list
    public ArrayList<String> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<String> gifts) {
        this.gifts = gifts;
    }

    // Add a gift to the user's gift list
    public void addGift(String gift) {
        gifts.add(gift);
    }
}
