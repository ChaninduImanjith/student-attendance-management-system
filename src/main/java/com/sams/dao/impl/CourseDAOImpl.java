package com.sams.dao.impl;
import com.sams.dao.CourseDAO;
import com.sams.entity.Course;
public class CourseDAOImpl extends GenericDAOImpl<Course, Integer> implements CourseDAO {
    public CourseDAOImpl() { super(Course.class); }
}
