package com.sams.controller;

import com.sams.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.scene.layout.StackPane;

public class AdminDashboardController {
    
    @FXML private StackPane contentArea;

    @FXML
    protected void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ViewUtil.switchScene(stage, "/view/login.fxml", "SAMS Login");
    }

    @FXML protected void showCourses() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/course_management.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    @FXML protected void showStudents() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/student_management.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    @FXML protected void showLecturers() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/lecturer_management.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    @FXML protected void showSchedule() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/class_management.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
