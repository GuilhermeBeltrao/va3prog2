package com.example.va3prog2.repositories;

import com.example.va3prog2.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsRepository {
    private static GroupsRepository instance;
    private List<Group> groups;

    private GroupsRepository() {
        groups = new ArrayList<>();
    }

    public static GroupsRepository getInstance() {
        if (instance == null) {
            instance = new GroupsRepository();
        }
        return instance;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getAllGroups() {
        return groups;
    }
}