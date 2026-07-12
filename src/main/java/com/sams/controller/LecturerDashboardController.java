package com.sams.controller;

import com.sams.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class LecturerDashboardController {
    
    @FXML private StackPane contentArea;

    @FXML
    protected void handleLogout(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ViewUtil.switchScene(stage, "/view/login.fxml", "SAMS Login");
    }

    @FXML protected void showMyClasses() { /* Implemented later */ }
    @FXML protected void showMarkAttendance() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/mark_attendance.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    @FXML protected void showReports() {
        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/attendance_reports.fxml"));
            contentArea.getChildren().setAll(root);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
