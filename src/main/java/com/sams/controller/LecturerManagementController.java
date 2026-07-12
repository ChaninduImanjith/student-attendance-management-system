package com.sams.controller;

import com.sams.entity.Lecturer;
import com.sams.service.LecturerService;
import com.sams.service.impl.LecturerServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LecturerManagementController {

    @FXML private TextField empIdField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;

    @FXML private TableView<Lecturer> lecturerTable;
    @FXML private TableColumn<Lecturer, Integer> colId;
    @FXML private TableColumn<Lecturer, String> colEmpId;
    @FXML private TableColumn<Lecturer, String> colName;
    @FXML private TableColumn<Lecturer, String> colEmail;
    @FXML private TableColumn<Lecturer, String> colPhone;

    private final LecturerService lecturerService = new LecturerServiceImpl();
    
    private ObservableList<Lecturer> lecturerList = FXCollections.observableArrayList();
    private Lecturer selectedLecturer;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLecturerId()).asObject());
        colEmpId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeId()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));

        lecturerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedLecturer = newSelection;
                empIdField.setText(newSelection.getEmployeeId());
                nameField.setText(newSelection.getName());
                emailField.setText(newSelection.getEmail());
                phoneField.setText(newSelection.getPhone());
            }
        });

        loadLecturers();
    }

    private void loadLecturers() {
        lecturerList.setAll(lecturerService.getAllLecturers());
        lecturerTable.setItems(lecturerList);
    }

    @FXML
    public void handleAddLecturer() {
        if (empIdField.getText().isEmpty() || nameField.getText().isEmpty()) return;
        Lecturer lecturer = new Lecturer(empIdField.getText(), nameField.getText(), emailField.getText(), phoneField.getText(), null);
        lecturerService.addLecturer(lecturer);
        loadLecturers();
        clearFields();
    }

    @FXML
    public void handleUpdateLecturer() {
        if (selectedLecturer != null) {
            selectedLecturer.setEmployeeId(empIdField.getText());
            selectedLecturer.setName(nameField.getText());
            selectedLecturer.setEmail(emailField.getText());
            selectedLecturer.setPhone(phoneField.getText());
            lecturerService.updateLecturer(selectedLecturer);
            loadLecturers();
            clearFields();
        }
    }

    @FXML
    public void handleDeleteLecturer() {
        if (selectedLecturer != null) {
            lecturerService.deleteLecturer(selectedLecturer.getLecturerId());
            loadLecturers();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedLecturer = null;
        empIdField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        lecturerTable.getSelectionModel().clearSelection();
    }
}
