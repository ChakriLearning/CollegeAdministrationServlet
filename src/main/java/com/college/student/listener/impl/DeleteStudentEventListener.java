package com.college.student.listener.impl;

import com.college.student.event.IEvent;
import com.college.student.event.handler.EventHandler;
import com.college.student.event.impl.AddStudentEvent;
import com.college.student.event.impl.DeleteStudentEvent;
import com.college.student.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteStudentEventListener implements IEventListener {
    private static final Logger logger = LoggerFactory.getLogger(DeleteStudentEventListener.class);

    @Override
    public <Event> void onEvent(Event event) {
        if (event instanceof DeleteStudentEvent) {
            DeleteStudentEvent deleteStudentEvent = (DeleteStudentEvent) event;
            logger.info("Student with RollNo {}",deleteStudentEvent.getStudent().getRollNo() + " has been Deleted");
            logger.info("Source : {}",deleteStudentEvent.getSource());
        }
    }

}
