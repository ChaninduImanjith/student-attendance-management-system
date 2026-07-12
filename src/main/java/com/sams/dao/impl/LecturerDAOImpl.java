package com.sams.dao.impl;
import com.sams.dao.LecturerDAO;
import com.sams.entity.Lecturer;
public class LecturerDAOImpl extends GenericDAOImpl<Lecturer, Integer> implements LecturerDAO {
    public LecturerDAOImpl() { super(Lecturer.class); }
}
