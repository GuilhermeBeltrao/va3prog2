package com.example.va3prog2.repositories;

import com.example.va3prog2.models.Group;
import com.example.va3prog2.models.SecretSantaAssignment;
import com.example.va3prog2.models.User;

import java.util.ArrayList;
import java.util.List;

public class GroupsRepository {
    private static GroupsRepository instance;
    private List<Group> groups;

    public GroupsRepository() {
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

    public void listGroups() {
        for (Group group : groups) {
            System.out.println(group.getName());
        }
    }

    public boolean addUserToGroup(String selectedGroup, User user) {
        Group group = findGroupByName(selectedGroup);

        if (group != null) {
            if (!group.getUsers().contains(user)) {
                group.getUsers().add(user);
                return true; // User added successfully
            } else {
                return false; // User is already in the group
            }
        } else {
            return false;
        }
    }

    public Group findGroupByName(String selectedGroup) {
        for (Group group : groups) {
            if (group.getName().equals(selectedGroup)) {
                return group;
            }
        }
        return null;
    }

    public List<String> getAllGroupNames() {
        List<String> groupNames = new ArrayList<>();
        for (Group group : groups) {
            groupNames.add(group.getName());
        }
        return groupNames;
    }


    public List<SecretSantaAssignment> getAssignments(String groupName, String userPassword) {
        List<SecretSantaAssignment> assignments = new ArrayList<>();

        Group group = GroupsRepository.getInstance().findGroupByName(groupName);

        if (group != null) {
            for (User user : group.getUsers()) {
                SecretSantaAssignment assignment = new SecretSantaAssignment(user, null); // Initialize with null receiver
                assignments.add(assignment);
            }
        }

        return assignments;
    }

}