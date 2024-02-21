package com.college.student.listener.impl;

import com.college.student.event.IEvent;
import com.college.student.event.handler.EventHandler;
import com.college.student.event.impl.AddStudentEvent;
import com.college.student.event.impl.GetStudentEvent;
import com.college.student.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetStudentEventListener implements IEventListener {
    private static final Logger logger = LoggerFactory.getLogger(GetStudentEventListener.class);
    @Override
    public <Event> void onEvent(Event event) {
        if (event instanceof GetStudentEvent) {
            GetStudentEvent getStudentEvent = (GetStudentEvent) event;
            logger.info("Student Data Received : {}",getStudentEvent.getStudent());
            logger.info("Source : {}",getStudentEvent);
        }
    }

}
