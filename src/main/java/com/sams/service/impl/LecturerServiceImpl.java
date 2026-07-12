package com.sams.service.impl;
import com.sams.dao.LecturerDAO;
import com.sams.dao.impl.LecturerDAOImpl;
import com.sams.entity.Lecturer;
import com.sams.service.LecturerService;
import java.util.List;

public class LecturerServiceImpl implements LecturerService {
    private final LecturerDAO lecturerDAO = new LecturerDAOImpl();
    
    @Override public void addLecturer(Lecturer lecturer) { lecturerDAO.save(lecturer); }
    @Override public void updateLecturer(Lecturer lecturer) { lecturerDAO.update(lecturer); }
    @Override public void deleteLecturer(int id) { lecturerDAO.delete(id); }
    @Override public Lecturer getLecturerById(int id) { return lecturerDAO.findById(id); }
    @Override public List<Lecturer> getAllLecturers() { return lecturerDAO.findAll(); }
}
