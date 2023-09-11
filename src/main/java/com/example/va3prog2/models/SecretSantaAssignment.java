package com.example.va3prog2.models;

public class SecretSantaAssignment {

    private User giver;
    private User receiver;

    public SecretSantaAssignment(User giver, User receiver) {
        this.giver = giver;
        this.receiver = receiver;
    }



    public User getGiver() {
        return giver;
    }
    public User getReceiver() {
        return receiver;
    }


}
