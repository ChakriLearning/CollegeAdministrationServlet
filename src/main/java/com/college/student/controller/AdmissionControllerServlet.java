package com.college.student.controller;

import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.college.student.controller.ListStudentServlet.logger;

public class AdmissionControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        logger.debug("AdmissionControllerServlet.java loaded at line 17");
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        String studentChoice = (String) request.getParameter("choice");
        PrintWriter out = response.getWriter();
        switch (studentChoice) {
            case "1" :  //add student
                response.sendRedirect("AddStudentData.html");
                break;
            case "2" : //list student data
                logger.debug("executes at line 50(display call) by administrationServlet");
                response.sendRedirect("ListStudentDataTable.html");
                logger.debug("executes at line 52(display call) by administrationServlet");
                break;
            case "3" : //delete student
                response.sendRedirect("GetRollNoFromClient.html?choice=3");
//                response.setContentType("text/html");
//                out.println("<!DOCTYPE html>");
//                out.println("<html lang=\"en\">");
//                out.println("<head>");
//                out.println("<meta charset=\"UTF-8\">");
//                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
//                out.println("<title>Delete Student</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>Delete Student</h1>");
//                out.println("<form action=\"deletestudent\" method=\"post\">");
//                out.println("Enter Student Roll No To Delete: <input type=\"text\" name=\"rollNo\" required><br><br>");
//                out.println("<input type=\"submit\" value=\"Delete Student\">");
//                out.println("</form>");
//                out.println("</body>");
//                out.println("</html>");
                break;
            case "4" : //update student
                response.sendRedirect("GetRollNoFromClient.html?choice=4");
//
                break;
            case "5" : //get student data
                 response.sendRedirect("GetRollNoFromClient.html?choice=5");
                 break;
            case "0" :
                System.exit(0);
        }
    }
}
