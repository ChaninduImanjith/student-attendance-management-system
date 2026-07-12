package com.sams.controller;

import com.sams.entity.Course;
import com.sams.entity.Student;
import com.sams.service.CourseService;
import com.sams.service.StudentService;
import com.sams.service.impl.CourseServiceImpl;
import com.sams.service.impl.StudentServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

public class StudentManagementController {

    @FXML private TextField regNoField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<Course> courseComboBox;

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, Integer> colId;
    @FXML private TableColumn<Student, String> colRegNo;
    @FXML private TableColumn<Student, String> colName;
    @FXML private TableColumn<Student, String> colEmail;
    @FXML private TableColumn<Student, String> colCourse;

    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    private Student selectedStudent;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        colRegNo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRegistrationNumber()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colCourse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse() != null ? cellData.getValue().getCourse().getCourseName() : ""));

        courseComboBox.setItems(FXCollections.observableArrayList(courseService.getAllCourses()));
        courseComboBox.setConverter(new StringConverter<Course>() {
            @Override
            public String toString(Course course) {
                return course == null ? "" : course.getCourseName();
            }
            @Override
            public Course fromString(String string) {
                return null;
            }
        });

        studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedStudent = newSelection;
                regNoField.setText(newSelection.getRegistrationNumber());
                nameField.setText(newSelection.getName());
                emailField.setText(newSelection.getEmail());
                phoneField.setText(newSelection.getPhone());
                courseComboBox.setValue(newSelection.getCourse());
            }
        });

        loadStudents();
    }

    private void loadStudents() {
        studentList.setAll(studentService.getAllStudents());
        studentTable.setItems(studentList);
    }

    @FXML
    public void handleAddStudent() {
        if (regNoField.getText().isEmpty() || nameField.getText().isEmpty() || courseComboBox.getValue() == null) return;
        Student student = new Student(regNoField.getText(), nameField.getText(), emailField.getText(), phoneField.getText(), courseComboBox.getValue());
        studentService.addStudent(student);
        loadStudents();
        clearFields();
    }

    @FXML
    public void handleUpdateStudent() {
        if (selectedStudent != null && courseComboBox.getValue() != null) {
            selectedStudent.setRegistrationNumber(regNoField.getText());
            selectedStudent.setName(nameField.getText());
            selectedStudent.setEmail(emailField.getText());
            selectedStudent.setPhone(phoneField.getText());
            selectedStudent.setCourse(courseComboBox.getValue());
            studentService.updateStudent(selectedStudent);
            loadStudents();
            clearFields();
        }
    }

    @FXML
    public void handleDeleteStudent() {
        if (selectedStudent != null) {
            studentService.deleteStudent(selectedStudent.getStudentId());
            loadStudents();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedStudent = null;
        regNoField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        courseComboBox.setValue(null);
        studentTable.getSelectionModel().clearSelection();
    }
}
