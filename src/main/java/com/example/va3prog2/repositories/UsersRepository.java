package com.example.va3prog2.repositories;

import com.example.va3prog2.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersRepository {
    private List<User> userList;

    public UsersRepository() {
        this.userList = new ArrayList<>();
    }

    public void createUser(String fullName, String password, String nickname) {
        User user = new User(fullName, password, nickname);
        userList.add(user);
    }

    public User getUserByNickname(String nickname) {
        for (User user : userList) {
            if (user.getNickname().equals(nickname)) {
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
}
