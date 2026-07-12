package com.sams.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private Integer attendanceId;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private ClassSchedule classSchedule;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "status", nullable = false)
    private String status; // 'PRESENT', 'ABSENT', 'LATE', 'EXCUSED'

    @Column(name = "remarks", length = 255)
    private String remarks;

    public Attendance() {}

    public Attendance(ClassSchedule classSchedule, Student student, String status, String remarks) {
        this.classSchedule = classSchedule;
        this.student = student;
        this.status = status;
        this.remarks = remarks;
    }

    public Integer getAttendanceId() { return attendanceId; }
    public void setAttendanceId(Integer attendanceId) { this.attendanceId = attendanceId; }

    public ClassSchedule getClassSchedule() { return classSchedule; }
    public void setClassSchedule(ClassSchedule classSchedule) { this.classSchedule = classSchedule; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
