package com.sams.service.impl;
import com.sams.dao.StudentDAO;
import com.sams.dao.impl.StudentDAOImpl;
import com.sams.entity.Student;
import com.sams.service.StudentService;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDAO studentDAO = new StudentDAOImpl();
    
    @Override public void addStudent(Student student) { studentDAO.save(student); }
    @Override public void updateStudent(Student student) { studentDAO.update(student); }
    @Override public void deleteStudent(int id) { studentDAO.delete(id); }
    @Override public Student getStudentById(int id) { return studentDAO.findById(id); }
    @Override public List<Student> getAllStudents() { return studentDAO.findAll(); }
}
