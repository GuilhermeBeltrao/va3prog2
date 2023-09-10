package com.example.va3prog2.models;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Date;

public class Group {
    private String name;
    private String date;
    private ArrayList<User> users; // ArrayList of User objects
    private ArrayList<User> sortedUsers; // ArrayList of sorted User objects

    // Constructor
    public Group(String name, String date) {
        this.name = name;
        this.date = date;
        this.users = new ArrayList<>();
        this.sortedUsers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and Setter methods for users and sortedUsers
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getSortedUsers() {
        return sortedUsers;
    }

    public void setSortedUsers(ArrayList<User> sortedUsers) {
        this.sortedUsers = sortedUsers;
    }

}