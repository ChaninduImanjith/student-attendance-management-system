package com.sams.service.impl;
import com.sams.dao.CourseDAO;
import com.sams.dao.impl.CourseDAOImpl;
import com.sams.entity.Course;
import com.sams.service.CourseService;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseDAO courseDAO = new CourseDAOImpl();
    
    @Override public void addCourse(Course course) { courseDAO.save(course); }
    @Override public void updateCourse(Course course) { courseDAO.update(course); }
    @Override public void deleteCourse(int id) { courseDAO.delete(id); }
    @Override public Course getCourseById(int id) { return courseDAO.findById(id); }
    @Override public List<Course> getAllCourses() { return courseDAO.findAll(); }
}
