package com.sams.service.impl;
import com.sams.dao.SubjectDAO;
import com.sams.dao.impl.SubjectDAOImpl;
import com.sams.entity.Subject;
import com.sams.service.SubjectService;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectDAO subjectDAO = new SubjectDAOImpl();
    
    @Override public void addSubject(Subject subject) { subjectDAO.save(subject); }
    @Override public void updateSubject(Subject subject) { subjectDAO.update(subject); }
    @Override public void deleteSubject(int id) { subjectDAO.delete(id); }
    @Override public Subject getSubjectById(int id) { return subjectDAO.findById(id); }
    @Override public List<Subject> getAllSubjects() { return subjectDAO.findAll(); }
}
