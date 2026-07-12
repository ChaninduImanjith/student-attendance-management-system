package com.sams.controller;

import com.sams.entity.Attendance;
import com.sams.entity.ClassSchedule;
import com.sams.entity.Student;
import com.sams.service.AttendanceService;
import com.sams.service.ClassScheduleService;
import com.sams.service.StudentService;
import com.sams.service.impl.AttendanceServiceImpl;
import com.sams.service.impl.ClassScheduleServiceImpl;
import com.sams.service.impl.StudentServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import java.util.List;
import java.util.stream.Collectors;

public class MarkAttendanceController {

    @FXML private ComboBox<ClassSchedule> classComboBox;
    @FXML private TableView<AttendanceRow> attendanceTable;
    @FXML private TableColumn<AttendanceRow, String> colRegNo;
    @FXML private TableColumn<AttendanceRow, String> colName;
    @FXML private TableColumn<AttendanceRow, String> colStatus;
    @FXML private TableColumn<AttendanceRow, String> colRemarks;

    private final ClassScheduleService classScheduleService = new ClassScheduleServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final AttendanceService attendanceService = new AttendanceServiceImpl();

    private ObservableList<AttendanceRow> attendanceList = FXCollections.observableArrayList();

    public static class AttendanceRow {
        public Student student;
        public SimpleStringProperty status = new SimpleStringProperty("PRESENT");
        public SimpleStringProperty remarks = new SimpleStringProperty("");

        public AttendanceRow(Student student) { this.student = student; }
        public String getStatus() { return status.get(); }
        public void setStatus(String s) { status.set(s); }
        public SimpleStringProperty statusProperty() { return status; }

        public String getRemarks() { return remarks.get(); }
        public void setRemarks(String r) { remarks.set(r); }
        public SimpleStringProperty remarksProperty() { return remarks; }
    }

    @FXML
    public void initialize() {
        attendanceTable.setEditable(true);
        colRegNo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().student.getRegistrationNumber()));
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().student.getName()));
        
        colStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        colStatus.setCellFactory(ComboBoxTableCell.forTableColumn("PRESENT", "ABSENT", "LATE", "EXCUSED"));
        
        colRemarks.setCellValueFactory(cellData -> cellData.getValue().remarksProperty());
        colRemarks.setCellFactory(TextFieldTableCell.forTableColumn());

        classComboBox.setItems(FXCollections.observableArrayList(classScheduleService.getAllClassSchedules()));
        classComboBox.setConverter(new StringConverter<ClassSchedule>() {
            @Override
            public String toString(ClassSchedule cs) {
                if (cs == null) return "";
                return cs.getCourse().getCourseCode() + " - " + cs.getSubject().getSubjectName() + " (" + cs.getClassDate() + ")";
            }
            @Override
            public ClassSchedule fromString(String string) { return null; }
        });
    }

    @FXML
    public void loadStudentsForClass() {
        ClassSchedule selectedClass = classComboBox.getValue();
        if (selectedClass != null) {
            List<Student> allStudents = studentService.getAllStudents();
            List<Student> classStudents = allStudents.stream()
                .filter(s -> s.getCourse().getCourseId().equals(selectedClass.getCourse().getCourseId()))
                .collect(Collectors.toList());

            attendanceList.clear();
            for (Student s : classStudents) {
                attendanceList.add(new AttendanceRow(s));
            }
            attendanceTable.setItems(attendanceList);
        }
    }

    @FXML
    public void handleSaveAttendance() {
        ClassSchedule selectedClass = classComboBox.getValue();
        if (selectedClass == null || attendanceList.isEmpty()) {
            return;
        }

        for (AttendanceRow row : attendanceList) {
            Attendance att = new Attendance(selectedClass, row.student, row.getStatus(), row.getRemarks());
            attendanceService.addAttendance(att);
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Attendance marked successfully!", ButtonType.OK);
        alert.show();
        
        attendanceList.clear();
        classComboBox.setValue(null);
    }
}
