package com.example.va3prog2.repositories;

import com.example.va3prog2.models.SecretSantaAssignment;
import com.example.va3prog2.models.User;

import java.util.ArrayList;
import java.util.List;

public class SecretSantaAssignmentRepository {
    private List<SecretSantaAssignment> assignments;

    private static SecretSantaAssignmentRepository instance;

    private SecretSantaAssignmentRepository() {
        assignments = new ArrayList<>();
    }

    public static SecretSantaAssignmentRepository getInstance() {
        if (instance == null) {
            instance = new SecretSantaAssignmentRepository();
        }
        return instance;
    }

    public void addAssignment(SecretSantaAssignment assignment) {
        assignments.add(assignment);
    }

    public SecretSantaAssignment getAssignmentForUser(User user) {
        for (SecretSantaAssignment assignment : assignments) {
            if (assignment.getGiver().equals(user)) {
                return assignment;
            }
        }
        return null;
    }

    // Add other methods as needed for managing assignments
}
