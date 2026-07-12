package com.sams.controller;

import com.sams.entity.ClassSchedule;
import com.sams.entity.Course;
import com.sams.entity.Lecturer;
import com.sams.entity.Subject;
import com.sams.service.ClassScheduleService;
import com.sams.service.CourseService;
import com.sams.service.LecturerService;
import com.sams.service.SubjectService;
import com.sams.service.impl.ClassScheduleServiceImpl;
import com.sams.service.impl.CourseServiceImpl;
import com.sams.service.impl.LecturerServiceImpl;
import com.sams.service.impl.SubjectServiceImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.LocalTime;

public class ClassManagementController {

    @FXML private ComboBox<Course> courseComboBox;
    @FXML private ComboBox<Subject> subjectComboBox;
    @FXML private ComboBox<Lecturer> lecturerComboBox;
    @FXML private DatePicker datePicker;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    @FXML private TableView<ClassSchedule> classTable;
    @FXML private TableColumn<ClassSchedule, Integer> colId;
    @FXML private TableColumn<ClassSchedule, String> colCourse;
    @FXML private TableColumn<ClassSchedule, String> colSubject;
    @FXML private TableColumn<ClassSchedule, String> colLecturer;
    @FXML private TableColumn<ClassSchedule, String> colDate;
    @FXML private TableColumn<ClassSchedule, String> colStartTime;
    @FXML private TableColumn<ClassSchedule, String> colEndTime;

    private final ClassScheduleService classScheduleService = new ClassScheduleServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();
    private final SubjectService subjectService = new SubjectServiceImpl();
    private final LecturerService lecturerService = new LecturerServiceImpl();
    
    private ObservableList<ClassSchedule> classList = FXCollections.observableArrayList();
    private ClassSchedule selectedClass;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getClassId()).asObject());
        colCourse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse() != null ? cellData.getValue().getCourse().getCourseCode() : ""));
        colSubject.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSubject() != null ? cellData.getValue().getSubject().getSubjectName() : ""));
        colLecturer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLecturer() != null ? cellData.getValue().getLecturer().getName() : ""));
        colDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassDate().toString()));
        colStartTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime().toString()));
        colEndTime.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndTime().toString()));

        courseComboBox.setItems(FXCollections.observableArrayList(courseService.getAllCourses()));
        courseComboBox.setConverter(new StringConverter<Course>() {
            @Override public String toString(Course course) { return course == null ? "" : course.getCourseName(); }
            @Override public Course fromString(String string) { return null; }
        });

        subjectComboBox.setItems(FXCollections.observableArrayList(subjectService.getAllSubjects()));
        subjectComboBox.setConverter(new StringConverter<Subject>() {
            @Override public String toString(Subject subject) { return subject == null ? "" : subject.getSubjectName(); }
            @Override public Subject fromString(String string) { return null; }
        });

        lecturerComboBox.setItems(FXCollections.observableArrayList(lecturerService.getAllLecturers()));
        lecturerComboBox.setConverter(new StringConverter<Lecturer>() {
            @Override public String toString(Lecturer lecturer) { return lecturer == null ? "" : lecturer.getName(); }
            @Override public Lecturer fromString(String string) { return null; }
        });

        classTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedClass = newSelection;
                courseComboBox.setValue(newSelection.getCourse());
                subjectComboBox.setValue(newSelection.getSubject());
                lecturerComboBox.setValue(newSelection.getLecturer());
                datePicker.setValue(newSelection.getClassDate());
                startTimeField.setText(newSelection.getStartTime().toString());
                endTimeField.setText(newSelection.getEndTime().toString());
            }
        });

        loadClasses();
    }

    private void loadClasses() {
        classList.setAll(classScheduleService.getAllClassSchedules());
        classTable.setItems(classList);
    }

    @FXML
    public void handleAddClass() {
        if (courseComboBox.getValue() == null || subjectComboBox.getValue() == null || lecturerComboBox.getValue() == null || datePicker.getValue() == null) return;
        try {
            LocalTime startTime = LocalTime.parse(startTimeField.getText());
            LocalTime endTime = LocalTime.parse(endTimeField.getText());
            ClassSchedule schedule = new ClassSchedule(courseComboBox.getValue(), subjectComboBox.getValue(), lecturerComboBox.getValue(), datePicker.getValue(), startTime, endTime);
            classScheduleService.addClassSchedule(schedule);
            loadClasses();
            clearFields();
        } catch (Exception e) {
            System.err.println("Time format must be HH:mm");
        }
    }

    @FXML
    public void handleUpdateClass() {
        if (selectedClass != null && courseComboBox.getValue() != null && subjectComboBox.getValue() != null && lecturerComboBox.getValue() != null && datePicker.getValue() != null) {
            try {
                selectedClass.setCourse(courseComboBox.getValue());
                selectedClass.setSubject(subjectComboBox.getValue());
                selectedClass.setLecturer(lecturerComboBox.getValue());
                selectedClass.setClassDate(datePicker.getValue());
                selectedClass.setStartTime(LocalTime.parse(startTimeField.getText()));
                selectedClass.setEndTime(LocalTime.parse(endTimeField.getText()));
                classScheduleService.updateClassSchedule(selectedClass);
                loadClasses();
                clearFields();
            } catch (Exception e) {
                System.err.println("Time format must be HH:mm");
            }
        }
    }

    @FXML
    public void handleDeleteClass() {
        if (selectedClass != null) {
            classScheduleService.deleteClassSchedule(selectedClass.getClassId());
            loadClasses();
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        selectedClass = null;
        courseComboBox.setValue(null);
        subjectComboBox.setValue(null);
        lecturerComboBox.setValue(null);
        datePicker.setValue(null);
        startTimeField.clear();
        endTimeField.clear();
        classTable.getSelectionModel().clearSelection();
    }
}
