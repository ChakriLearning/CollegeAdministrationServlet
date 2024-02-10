package com.college.student.controller;

import com.college.student.service.StudentService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SetStorageTypeServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String storageType = request.getParameter("storageType");
        StudentService studentService = new StudentService(storageType);
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("studentService",studentService);
        try {
            response.sendRedirect("StudentChoices.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
