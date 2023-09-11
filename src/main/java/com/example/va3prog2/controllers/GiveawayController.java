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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class GiveawayController {
    public TextField groupNameTextField;

    public ChoiceBox<String> personChoiceBox;


    public void performGiftExchange(ActionEvent event) {
        String selectedGroup = groupNameTextField.getText();

        if (selectedGroup != null) {
            Group group = GroupsRepository.getInstance().findGroupByName(selectedGroup);
            if (group != null) {
                LocalDate giftExchangeDate = parseGiftExchangeDate(group.getGiftExchangeDate());
                if (giftExchangeDate != null) {
                    LocalDate currentDate = LocalDate.now();
                    if (currentDate.isAfter(giftExchangeDate)) {
                        List<SecretSantaAssignment> assignments = generateSecretSantaAssignments(group);
                        showGiftExchangeResults(assignments);
                    } else {
                        showAlert("Gift Exchange Not Available", "The gift exchange date has not arrived yet.");
                    }
                } else {
                    showAlert("Gift Exchange Date Error", "The gift exchange date is not in the correct format.");
                }
            } else {
                showAlert("Error", "Invalid group.");
            }
        } else {
            showAlert("Error", "Invalid group.");
        }
    }

    private void showGiftExchangeResults(List<SecretSantaAssignment> assignments) {
        StringBuilder message = new StringBuilder();
        for (SecretSantaAssignment assignment : assignments) {
            message.append(assignment.getGiver().getNickname())
                    .append(" gives a gift to ")
                    .append(assignment.getReceiver().getNickname())
                    .append("\n");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gift Exchange Results");
        alert.setHeaderText(null);
        alert.setContentText(message.toString());
        alert.showAndWait();
    }

    private LocalDate parseGiftExchangeDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private List<SecretSantaAssignment> generateSecretSantaAssignments(Group group) {
        List<SecretSantaAssignment> giftExchange = new ArrayList<>();
        List<User> members = new ArrayList<>(group.getUsers());
        Collections.shuffle(members, new Random());

        for (int i = 0; i < members.size(); i++) {
            User giver = members.get(i);
            User receiver = members.get((i + 1) % members.size()); // Ensure the last person does not give to the first
            SecretSantaAssignment assignment = new SecretSantaAssignment(giver, receiver);
            giftExchange.add(assignment);
        }

        return giftExchange;
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
//        showGiftExchangeResults(assignments);
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
