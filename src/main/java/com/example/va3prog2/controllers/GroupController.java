package com.example.va3prog2.controllers;

import com.example.va3prog2.models.Group;
import com.example.va3prog2.repositories.GroupsRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class GroupController {
    @FXML
    private TextField groupNameTextField;

    @FXML
    private DatePicker groupDatePicker;

    @FXML
    private void saveGroup(ActionEvent event) {
        String groupName = groupNameTextField.getText();
        if (groupName.isEmpty()) {
            showAlert("Group Name Error", "Group name cannot be empty.");
        } else {
            // Get the selected date from the DatePicker
            String selectedDate = groupDatePicker.getValue().toString();

            Group group = new Group(groupName, selectedDate);
            GroupsRepository.getInstance().addGroup(group);

            //clear the input fields
            groupNameTextField.clear();
            groupDatePicker.getEditor().clear();
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
