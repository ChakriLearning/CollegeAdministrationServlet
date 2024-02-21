package com.college.student.listener.impl;

import com.college.student.event.impl.GetAllStudentEvent;
import com.college.student.listener.IEventListener;
import com.college.student.pojo.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllStudentEventListener implements IEventListener<GetAllStudentEvent> {
    private static final Logger logger = LoggerFactory.getLogger(GetAllStudentEventListener.class);

    @Override
    public void onEvent(GetAllStudentEvent getAllStudentEvent) {
        List<Student> studentList = getAllStudentEvent.getStudentList();
        logger.info("Received Request for Students List");
        logger.info("Student List are : ");
        for (Student student : studentList) logger.info(String.valueOf(student));
        logger.info("Source : {}", getAllStudentEvent.getSource());
    }
}