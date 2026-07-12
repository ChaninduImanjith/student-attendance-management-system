package com.sams.service;
import com.sams.entity.ClassSchedule;
import java.util.List;
public interface ClassScheduleService {
    void addClassSchedule(ClassSchedule classSchedule);
    void updateClassSchedule(ClassSchedule classSchedule);
    void deleteClassSchedule(int id);
    ClassSchedule getClassScheduleById(int id);
    List<ClassSchedule> getAllClassSchedules();
}
