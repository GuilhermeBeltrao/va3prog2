package com.example.va3prog2.controllers;

import com.example.va3prog2.MainApplication;
import com.example.va3prog2.models.Group;
import com.example.va3prog2.models.SecretSantaAssignment;
import com.example.va3prog2.models.User;
import com.example.va3prog2.repositories.GroupsRepository;
import com.example.va3prog2.repositories.UsersRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class GiveawayController {
    public ChoiceBox<String> groupChoiceBox;

    public ChoiceBox<String> personChoiceBox;

    public PasswordField passwordField;

    public void performGiftExchange(ActionEvent event) {
        String selectedGroup = groupChoiceBox.getValue();
        String selectedPerson = personChoiceBox.getValue();
        String password = passwordField.getText();

        if (selectedGroup != null && selectedPerson != null && !password.isEmpty()) {
            // Check if the group is valid and the password is correct
            Group group = GroupsRepository.getInstance().findGroupByName(selectedGroup);
            if (group != null && UsersRepository.getInstance().getUserByPassword(password) != null) {
                // Perform the gift exchange
                List<SecretSantaAssignment> assignments = performGiftExchange(selectedGroup);

                // Display the results
                showGiftExchangeResults(assignments);
            } else {
                showAlert("Error", "Invalid group or password.");
            }
        } else {
            // Show an error message if any required field is not filled
            showAlert("Error", "Please fill in all fields.");
        }
    }


    public List<SecretSantaAssignment> performGiftExchange(String groupName) {
        List<SecretSantaAssignment> giftExchange = new ArrayList<>();

        Group group = GroupsRepository.getInstance().findGroupByName(groupName);

        if (group != null) {
            String giftExchangeDateString = group.getGiftExchangeDate();
            try {
                LocalDate giftExchangeDate = LocalDate.parse(giftExchangeDateString);
                LocalDate currentDate = LocalDate.now();
                if (currentDate.isAfter(giftExchangeDate)) {
                    List<SecretSantaAssignment> assignments = performGiftExchange(groupName);
                } else {
                    showAlert("Gift Exchange Not Available", "The gift exchange date has not arrived yet.");
                }
            } catch (DateTimeParseException e) {
                showAlert("Gift Exchange Date Error", "The gift exchange date is not in the correct format.");
            }

            List<User> members = new ArrayList<>(group.getUsers());
            Collections.shuffle(members, new Random());

            for (int i = 0; i < members.size(); i++) {
                User giver = members.get(i);
                User receiver = members.get((i + 1) % members.size()); // Ensure the last person does not give to the first
                SecretSantaAssignment assignment = new SecretSantaAssignment(giver, receiver);
                giftExchange.add(assignment);
            }

        }
        return giftExchange;
    }

    private void showGiftExchangeResults(List<SecretSantaAssignment> assignments) {
        System.out.println("Gift Exchange Results:");

        for (SecretSantaAssignment assignment : assignments) {
            User giver = assignment.getGiver();
            User receiver = assignment.getReceiver();

            System.out.println(giver.getNickname() + " is giving a gift to " + receiver.getNickname() + ":");

            List<String> receiverGifts = receiver.getGifts();
            for (String gift : receiverGifts) {
                System.out.println("- " + gift);
            }

            System.out.println(); // Add an empty line to separate assignments
        }
    }


    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

//    public void consultSecretSantaAssignments(String groupName, String userPassword) {
//        // Retrieve Secret Santa assignments for the user using the repository
//        List<SecretSantaAssignment> assignments = GroupsRepository.getInstance().getAssignments(groupName, userPassword);
//
//        // Display the assignments to the user
//    }



    public void loadRegister (ActionEvent event) throws IOException {
        changeScene("registerPage.fxml", event);

    }

    public void loadGroups (ActionEvent event) throws IOException {
        changeScene("groupPage.fxml", event);
    }

    public void loadGiveaway (ActionEvent event) throws IOException {
        changeScene("giveawayPage.fxml", event);
    }

    public void loadListUsers (ActionEvent event) throws IOException {
        changeScene("giftsPage.fxml", event);
    }

    private void changeScene(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
