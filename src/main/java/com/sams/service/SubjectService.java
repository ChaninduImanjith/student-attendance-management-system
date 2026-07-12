package com.sams.service;
import com.sams.entity.Subject;
import java.util.List;
public interface SubjectService {
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(int id);
    Subject getSubjectById(int id);
    List<Subject> getAllSubjects();
}
