package com.example.va3prog2.controllers;

import com.example.va3prog2.MainApplication;
import com.example.va3prog2.models.Group;
import com.example.va3prog2.models.User;
import com.example.va3prog2.repositories.GroupsRepository;
import com.example.va3prog2.repositories.UsersRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GroupController {
    public TextField groupNameTextField;

    public DatePicker groupDatePicker;


    public TableView<Group> groupUserTableView;

    public TextField nicknameField;

    public TextField newUserField;
    public ListView<String> groupListView;


    private UsersRepository usersRepository;

    private GroupsRepository groupsRepository;




    public GroupController() {
        this.usersRepository = UsersRepository.getInstance();
        this.groupsRepository = GroupsRepository.getInstance();
    }

    public void saveGroup(ActionEvent event) {
        String groupName = groupNameTextField.getText();
        if (groupName.isEmpty()) {
            showAlert("Group Name Error", "Group name cannot be empty.");
        } else {
            // Get the selected date from the DatePicker
            String selectedDate = groupDatePicker.getValue().toString();

            Group group = new Group(groupName, selectedDate);
            GroupsRepository.getInstance().addGroup(group);

            //list all groups in the console
            GroupsRepository.getInstance().listGroups();
            //clear the input fields
            groupNameTextField.clear();
            groupDatePicker.getEditor().clear();

            System.out.println(usersRepository.getAllUsers());
        }
    }

    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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


    public void initialize() {
        // Load and display groups in the groupListView
        List<String> groupNames = GroupsRepository.getInstance().getAllGroupNames();
        groupListView.setItems(FXCollections.observableArrayList(groupNames));

    }




    public void addUserToGroup(ActionEvent event) {
        String selectedUserName = nicknameField.getText();
        String selectedGroup = (String) groupListView.getSelectionModel().getSelectedItem();

        if (selectedUserName == null || selectedGroup == null) {
            showAlert("Error", "Please select a user and a group.");
        } else {
            // Find the user in the repository by name
            User user = UsersRepository.getInstance().getUserByNickname(selectedUserName);
            if (user != null) {
                // Add the user to the selected group
                boolean added = GroupsRepository.getInstance().addUserToGroup(selectedGroup, user);

                if (added) {
                    showAlert("User Added to Group", selectedUserName + " added to group: " + selectedGroup);
                } else {
                    showAlert("Error", "Failed to add the user to the group.");
                }
            } else {
                showAlert("Error", "User not found in the repository.");
            }
        }
    }




}
