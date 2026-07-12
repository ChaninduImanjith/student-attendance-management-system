package com.sams.dao.impl;
import com.sams.dao.AttendanceDAO;
import com.sams.entity.Attendance;
public class AttendanceDAOImpl extends GenericDAOImpl<Attendance, Integer> implements AttendanceDAO {
    public AttendanceDAOImpl() { super(Attendance.class); }
}
