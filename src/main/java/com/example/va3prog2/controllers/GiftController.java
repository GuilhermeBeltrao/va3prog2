package com.example.va3prog2.controllers;

import com.example.va3prog2.models.Gift;
import com.example.va3prog2.repositories.GiftsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.va3prog2.MainApplication;

import java.io.IOException;

public class GiftController {
    public TextField nicknameField;
    public TextField categoryField;
    public TextField descriptionField;
    public TextField priceField;

    public void saveGift(ActionEvent event) {
        String nickname = nicknameField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        String priceText = priceField.getText();

        // Validate the input fields
        if (nickname.isEmpty() || category.isEmpty() || description.isEmpty() || priceText.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            Gift gift = new Gift(category, description, price, nickname);
            GiftsRepository.getInstance().addGift(gift);

            showAlert("Gift Created", "Gift has been created successfully.");
            nicknameField.clear();
            categoryField.clear();
            descriptionField.clear();
            priceField.clear();

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid price format. Please enter a valid number.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
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
