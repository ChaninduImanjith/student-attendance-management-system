package com.sams.service.impl;
import com.sams.dao.AttendanceDAO;
import com.sams.dao.impl.AttendanceDAOImpl;
import com.sams.entity.Attendance;
import com.sams.service.AttendanceService;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
    
    @Override public void addAttendance(Attendance attendance) { attendanceDAO.save(attendance); }
    @Override public void updateAttendance(Attendance attendance) { attendanceDAO.update(attendance); }
    @Override public void deleteAttendance(int id) { attendanceDAO.delete(id); }
    @Override public Attendance getAttendanceById(int id) { return attendanceDAO.findById(id); }
    @Override public List<Attendance> getAllAttendances() { return attendanceDAO.findAll(); }
}
