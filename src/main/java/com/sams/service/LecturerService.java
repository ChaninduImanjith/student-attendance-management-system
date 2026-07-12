package com.sams.service;
import com.sams.entity.Lecturer;
import java.util.List;
public interface LecturerService {
    void addLecturer(Lecturer lecturer);
    void updateLecturer(Lecturer lecturer);
    void deleteLecturer(int id);
    Lecturer getLecturerById(int id);
    List<Lecturer> getAllLecturers();
}
