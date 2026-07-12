package com.sams.service;
import com.sams.entity.Course;
import java.util.List;
public interface CourseService {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int id);
    Course getCourseById(int id);
    List<Course> getAllCourses();
}
