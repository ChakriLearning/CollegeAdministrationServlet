package com.college.student.listener.impl;

import com.college.student.event.IEvent;
import com.college.student.event.impl.AddStudentEvent;
import com.college.student.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddStudentEventListener implements IEventListener {
    private static final Logger logger = LoggerFactory.getLogger(AddStudentEventListener.class);
    @Override
    public void onEvent(IEvent event) {
        if (event instanceof AddStudentEvent) {  //return true if event is an instance of AddStudentEvent - IEvent event = new AddStudentEvent so event is an instance of AddStudentEvent;
            AddStudentEvent addStudentEvent = (AddStudentEvent) event; // we have data in AddStudentEvent so we need to access this
            logger.info("New Student Added");
            logger.info("Student Details : {}",addStudentEvent.getStudent());
            logger.info("Source : {}",addStudentEvent.getSource());
        }
    }
}
