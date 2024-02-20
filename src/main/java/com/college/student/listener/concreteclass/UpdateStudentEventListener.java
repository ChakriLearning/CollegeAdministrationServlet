package com.college.student.listener.concreteclass;

import com.college.student.listener.Listener;
import com.college.student.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateStudentEventListener implements Listener {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStudentEventListener.class);
    @Override
    public void onEvent(Student student) {
        logger.info("Student with RollNo : {}",student.getRollNo() + " is Updated");
        logger.info("Details are : {}", student);
    }
}
