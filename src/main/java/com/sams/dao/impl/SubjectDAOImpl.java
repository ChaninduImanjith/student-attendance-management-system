package com.sams.dao.impl;
import com.sams.dao.SubjectDAO;
import com.sams.entity.Subject;
public class SubjectDAOImpl extends GenericDAOImpl<Subject, Integer> implements SubjectDAO {
    public SubjectDAOImpl() { super(Subject.class); }
}
