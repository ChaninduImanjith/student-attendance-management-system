package com.sams.controller;

import com.sams.entity.Attendance;
import com.sams.entity.Course;
import com.sams.entity.Student;
import com.sams.service.AttendanceService;
import com.sams.service.CourseService;
import com.sams.service.StudentService;
import com.sams.service.impl.AttendanceServiceImpl;
import com.sams.service.impl.CourseServiceImpl;
import com.sams.service.impl.StudentServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceReportsController {

    @FXML private ComboBox<Student> studentComboBox;
    @FXML private ComboBox<Course> courseComboBox;

    @FXML private TableView<Attendance> reportTable;
    @FXML private TableColumn<Attendance, String> colDate;
    @FXML private TableColumn<Attendance, String> colCourse;
    @FXML private TableColumn<Attendance, String> colSubject;
    @FXML private TableColumn<Attendance, String> colStudent;
    @FXML private TableColumn<Attendance, String> colStatus;
    @FXML private TableColumn<Attendance, String> colRemarks;

    private final AttendanceService attendanceService = new AttendanceServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final CourseService courseService = new CourseServiceImpl();

    private ObservableList<Attendance> reportList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassSchedule().getClassDate().toString()));
        colCourse.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassSchedule().getCourse().getCourseCode()));
        colSubject.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClassSchedule().getSubject().getSubjectName()));
        colStudent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudent().getName()));
        colStatus.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatus()));
        colRemarks.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemarks()));

        studentComboBox.setItems(FXCollections.observableArrayList(studentService.getAllStudents()));
        studentComboBox.setConverter(new StringConverter<Student>() {
            @Override public String toString(Student s) { return s == null ? "" : s.getName(); }
            @Override public Student fromString(String string) { return null; }
        });

        courseComboBox.setItems(FXCollections.observableArrayList(courseService.getAllCourses()));
        courseComboBox.setConverter(new StringConverter<Course>() {
            @Override public String toString(Course c) { return c == null ? "" : c.getCourseName(); }
            @Override public Course fromString(String string) { return null; }
        });

        loadAllReports();
    }

    private void loadAllReports() {
        reportList.setAll(attendanceService.getAllAttendances());
        reportTable.setItems(reportList);
    }

    @FXML
    public void handleGenerateReport() {
        List<Attendance> all = attendanceService.getAllAttendances();
        Student selStudent = studentComboBox.getValue();
        Course selCourse = courseComboBox.getValue();

        List<Attendance> filtered = all.stream()
            .filter(a -> selStudent == null || a.getStudent().getStudentId().equals(selStudent.getStudentId()))
            .filter(a -> selCourse == null || a.getClassSchedule().getCourse().getCourseId().equals(selCourse.getCourseId()))
            .collect(Collectors.toList());

        reportList.setAll(filtered);
    }

    @FXML
    public void handleClearFilters() {
        studentComboBox.setValue(null);
        courseComboBox.setValue(null);
        loadAllReports();
    }
}
