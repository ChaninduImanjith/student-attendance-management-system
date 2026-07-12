package com.sams.dao.impl;
import com.sams.dao.ClassScheduleDAO;
import com.sams.entity.ClassSchedule;
public class ClassScheduleDAOImpl extends GenericDAOImpl<ClassSchedule, Integer> implements ClassScheduleDAO {
    public ClassScheduleDAOImpl() { super(ClassSchedule.class); }
}
