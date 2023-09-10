package com.example.va3prog2.controllers;

import com.example.va3prog2.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {


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