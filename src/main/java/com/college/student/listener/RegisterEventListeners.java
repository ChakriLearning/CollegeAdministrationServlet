package com.college.student.listener;

import com.college.student.event.handler.EventHandler;
import com.college.student.event.impl.*;
import com.college.student.listener.impl.*;
import com.college.student.pojo.ErrorResponse;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterEventListeners implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(RegisterEventListeners.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Context Initialized");
        EventHandler.getInstance().registerListener(AddStudentEvent.class, new AddStudentEventListener());
        EventHandler.getInstance().registerListener(DeleteStudentEvent.class, new DeleteStudentEventListener());
        EventHandler.getInstance().registerListener(GetAllStudentEvent.class, new GetAllStudentEventListener());
        EventHandler.getInstance().registerListener(GetStudentEvent.class, new GetStudentEventListener());
        EventHandler.getInstance().registerListener(UpdateStudentEvent.class, new UpdateStudentEventListener());
        logger.info("Listeners Added to Specific Events");

        new Thread(() -> {
            logger.info("New Thread Started in contextInitialized()");
            try {
                EventHandler.invokeListeners();
            } catch (InterruptedException e) {
                ErrorResponse errorResponse = new ErrorResponse(HttpServletResponse.SC_CONTINUE, e.getMessage());
                logger.info("Exception Occurred while initialize invokeListeners() : {}", errorResponse);
            }
        }).start();

    }
}
