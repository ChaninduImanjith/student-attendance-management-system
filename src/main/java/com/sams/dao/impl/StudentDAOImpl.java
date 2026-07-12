package com.sams.dao.impl;
import com.sams.dao.StudentDAO;
import com.sams.entity.Student;
public class StudentDAOImpl extends GenericDAOImpl<Student, Integer> implements StudentDAO {
    public StudentDAOImpl() { super(Student.class); }
}
