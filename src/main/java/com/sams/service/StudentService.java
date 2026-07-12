package com.sams.service;
import com.sams.entity.Student;
import java.util.List;
public interface StudentService {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}
