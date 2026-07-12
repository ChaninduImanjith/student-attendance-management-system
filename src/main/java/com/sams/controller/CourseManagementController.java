package com.sams.controller;

import com.sams.entity.Course;
import com.sams.service.CourseService;
import com.sams.service.impl.CourseServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CourseManagementController {

    @FXML private TextField codeField;
    @FXML private TextField nameField;
    @FXML private TextField descField;

    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, Integer> colId;
    @FXML private TableColumn<Course, String> colCode;
    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, String> colDesc;

    private final CourseService courseService = new CourseServiceImpl();
    private ObservableList<Course> courseList = FXCollections.observableArrayList();
    private Course selectedCourse;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCourseId()).asObject());
        colCode.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseCode()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));
        colDesc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        courseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedCourse = newSelection;
                codeField.setText(newSelection.getCourseCode());
                nameField.setText(newSelection.getCourseName());
                descField.setText(newSelection.getDescription());
            }
        });

        loadCourses();
    }

    private void loadCourses() {
        courseList.setAll(courseService.getAllCourses());
        courseTable.setItems(courseList);
    }

    @FXML
    public void handleAddCourse() {
        if (codeField.getText().isEmpty() || nameField.getText().isEmpty()) return;
        Course course = new Course(codeField.getText(), nameField.getText(), descField.getText());
        courseService.addCourse(course);
        loadCourses();
        clearFields();
    }

    @FXML
    public void handleUpdateCourse() {
        if (selectedCourse != null) {
            selectedCourse.setCourseCode(codeField.getText());
            selectedCourse.setCourseName(nameField.getText());
            selectedCourse.setDescription(descField.getText());
            courseService.updateCourse(selectedCourse);
            loadCourses();
            clearFields();
        }
    }

    @FXML
    public void handleDeleteCourse() {
        if (selectedCourse != null) {
            courseService.deleteCourse(selectedCourse.getCourseId());
            loadCourses();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedCourse = null;
        codeField.clear();
        nameField.clear();
        descField.clear();
        courseTable.getSelectionModel().clearSelection();
    }
}
