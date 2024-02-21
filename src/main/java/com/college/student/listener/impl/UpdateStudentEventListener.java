package com.college.student.listener.impl;

import com.college.student.event.IEvent;
import com.college.student.event.impl.UpdateStudentEvent;
import com.college.student.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateStudentEventListener implements IEventListener {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStudentEventListener.class);

    @Override
    public void onEvent(IEvent event) {
        if (event instanceof UpdateStudentEvent) {
            UpdateStudentEvent updateStudentEvent = (UpdateStudentEvent) event;
            logger.info("Student With rollNo : {}", updateStudentEvent.getStudent().getRollNo() + " has been Updated");
            logger.info("Source : {}", updateStudentEvent.getSource());
        }
    }
}
