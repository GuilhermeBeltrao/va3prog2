package com.example.va3prog2.controllers;

import com.example.va3prog2.MainApplication;
import com.example.va3prog2.models.User;
import com.example.va3prog2.repositories.UsersRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class UserController {
    public Button registrationButton;

    public TextField fullNameField;

    public TextField passwordField;

    public TextField nicknameField;

    private UsersRepository usersRepository;

    public UserController() {
        this.usersRepository = UsersRepository.getInstance();
    }

    public void registerUser(ActionEvent actionEvent) {
        String fullName = fullNameField.getText();
        String nickname = nicknameField.getText();
        String password = passwordField.getText();

        if (fullName.isEmpty() || nickname.isEmpty() || password.isEmpty()) {
            showAlert("Please fill in all fields.");
        } else {
            usersRepository.createUser(fullName, password, nickname);
            showAlert("User registered successfully!");
            System.out.println("User registered successfully!");
            User user = usersRepository.getUserByNickname(nickname);
            System.out.println(user.getFullName());
        }
    }

    // Other methods for user management can also use usersRepository.

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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