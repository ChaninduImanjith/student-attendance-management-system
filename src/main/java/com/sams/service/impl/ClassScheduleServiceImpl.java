package com.sams.service.impl;
import com.sams.dao.ClassScheduleDAO;
import com.sams.dao.impl.ClassScheduleDAOImpl;
import com.sams.entity.ClassSchedule;
import com.sams.service.ClassScheduleService;
import java.util.List;

public class ClassScheduleServiceImpl implements ClassScheduleService {
    private final ClassScheduleDAO classScheduleDAO = new ClassScheduleDAOImpl();
    
    @Override public void addClassSchedule(ClassSchedule classSchedule) { classScheduleDAO.save(classSchedule); }
    @Override public void updateClassSchedule(ClassSchedule classSchedule) { classScheduleDAO.update(classSchedule); }
    @Override public void deleteClassSchedule(int id) { classScheduleDAO.delete(id); }
    @Override public ClassSchedule getClassScheduleById(int id) { return classScheduleDAO.findById(id); }
    @Override public List<ClassSchedule> getAllClassSchedules() { return classScheduleDAO.findAll(); }
}
