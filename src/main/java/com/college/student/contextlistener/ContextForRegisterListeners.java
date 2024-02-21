package com.college.student.contextlistener;

import com.college.student.event.handler.EventHandler;
import com.college.student.event.impl.*;
import com.college.student.listener.impl.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextForRegisterListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        EventHandler.getInstance().registerListener(AddStudentEvent.class, new AddStudentEventListener());
        EventHandler.getInstance().registerListener(DeleteStudentEvent.class, new DeleteStudentEventListener());
        EventHandler.getInstance().registerListener(GetAllStudentEvent.class, new GetAllStudentEventListener());
        EventHandler.getInstance().registerListener(GetStudentEvent.class, new GetStudentEventListener());
        EventHandler.getInstance().registerListener(UpdateStudentEvent.class, new UpdateStudentEventListener());
    }
}
