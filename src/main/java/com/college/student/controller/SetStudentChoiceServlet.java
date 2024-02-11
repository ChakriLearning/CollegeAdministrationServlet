package com.college.student.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SetStudentChoiceServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //make studentChoice as global attribute , everytime it calls this method it'll replace the studentChoice value;
        request.getServletContext().setAttribute("studentChoice", request.getParameter("choice"));
        //now calling the AdmissionControlServlet class
        request.getRequestDispatcher("control").include(request,response);
    }
}
