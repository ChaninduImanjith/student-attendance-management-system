package com.sams.service;
import com.sams.entity.Attendance;
import java.util.List;
public interface AttendanceService {
    void addAttendance(Attendance attendance);
    void updateAttendance(Attendance attendance);
    void deleteAttendance(int id);
    Attendance getAttendanceById(int id);
    List<Attendance> getAllAttendances();
}
