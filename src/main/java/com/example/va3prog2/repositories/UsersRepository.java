package com.example.va3prog2.repositories;

import com.example.va3prog2.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private List<User> userList;

    private static UsersRepository instance;


    public UsersRepository() {
        this.userList = new ArrayList<>();
    }


    public static UsersRepository getInstance() {
        if (instance == null) {
            instance = new UsersRepository();
        }
        return instance;
    }


    public void createUser(String fullName, String password, String nickname) {
        User user = new User(fullName, password, nickname);
        userList.add(user);
    }

    public User getUserByNickname(String nickname) {
        for (User user : userList) {
            if (user.getNickname().equalsIgnoreCase(nickname)) {
                return user;
            }
        }
        return null; // User not found
    }

    public void updateUserFullName(String nickname, String newFullName) {
        User user = getUserByNickname(nickname);
        if (user != null) {
            user.setFullName(newFullName);
        }
    }

    public void deleteUser(String nickname) {
        User user = getUserByNickname(nickname);
        if (user != null) {
            userList.remove(user);
        }
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public List<String> getAllUserNames() {
        List<String> userNames = new ArrayList<>();
        for (User user : userList) {
            userNames.add(user.getNickname());
        }
        return userNames;
    }

    public User getUserByPassword(String password) {
        for (User user : userList) {
            if (user.getPassword().equalsIgnoreCase(password)) {
                return user;
            }
        }
        return null; // User not found
    }
}
