package com.sams.controller;

import com.sams.entity.User;
import com.sams.service.UserService;
import com.sams.service.impl.UserServiceImpl;
import com.sams.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private final UserService userService = new UserServiceImpl();

    @FXML
    protected void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please enter username and password.");
            errorLabel.setVisible(true);
            return;
        }

        User user = userService.authenticate(username, password);

        if (user != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if ("Admin".equalsIgnoreCase(user.getRole())) {
                ViewUtil.switchScene(stage, "/view/admin_dashboard.fxml", "Admin Dashboard - SAMS");
            } else if ("Lecturer".equalsIgnoreCase(user.getRole())) {
                ViewUtil.switchScene(stage, "/view/lecturer_dashboard.fxml", "Lecturer Dashboard - SAMS");
            }
        } else {
            errorLabel.setText("Invalid username or password.");
            errorLabel.setVisible(true);
        }
    }
}
