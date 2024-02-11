package com.college.student.controller;

import com.college.student.service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import static com.college.student.controller.ListStudentServlet.logger;

public class AdmissionControllerServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        String studentChoice = (String) request.getParameter("choice");
        PrintWriter out = response.getWriter();
        switch (studentChoice) {
            case "1" :  //add student
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Add Student</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Add Student</h1>");
                out.println("<form action=\"addstudent\" method=\"post\">");
                out.println("<label for=\"rollNo\">Student Roll No:</label>");
                out.println("<input type=\"number\" id=\"rollNo\" name=\"rollNo\" required><br><br>");
                out.println("<label for=\"name\">Student Name:</label>");
                out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
                out.println("<label for=\"age\">Student Age:</label>");
                out.println("<input type=\"number\" id=\"age\" name=\"age\" required><br><br>");
                out.println("<label for=\"phoneNo\">Student Phone Number:</label>");
                out.println("<input type=\"tel\" id=\"phoneNo\" name=\"phoneNo\" required><br><br>");
                out.println("<input type=\"submit\" value=\"Add Student\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                break;
            case "2" : //list student data
                request.getRequestDispatcher("liststudents").include(request,response);
                break;
            case "3" : //delete student
                response.setContentType("text/html");
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Delete Student</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Delete Student</h1>");
                out.println("<form action=\"deletestudent\" method=\"post\">");
                out.println("Enter Student Roll No To Delete: <input type=\"text\" name=\"rollNo\" required><br><br>");
                out.println("<input type=\"submit\" value=\"Delete Student\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                break;
            case "4" : //update student
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Update Student Details</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Update Student Details</h1>");
                out.println("<form action=\"updatestudent\" method=\"post\">");
                out.println("Enter Student Roll No which need's to be updated: <input type=\"text\" name=\"rollNo\" required><br><br>");
                out.println("Enter New Student Name to update: <input type=\"text\" name=\"name\" required><br><br>");
                out.println("Enter New Student Age to update: <input type=\"number\" name=\"age\" required><br><br>");
                out.println("Enter New Student Phone Number to update: <input type=\"tel\" name=\"phoneNo\" required><br><br>");
                out.println("<input type=\"submit\" value=\"Update Student\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
                break;
            case "5" : //get student data
                response.setContentType("text/html");
                out.println("<!DOCTYPE html>");
                out.println("<html lang=\"en\">");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                out.println("<title>Delete Student</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Delete Student</h1>");
                out.println("<form action=\"getstudent\" method=\"post\">");
                out.println("Enter Student Roll No To Get his/her Detail's: <input type=\"text\" name=\"rollNo\" required><br><br>");
                out.println("<input type=\"submit\" value=\"Get Student\">");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            case "0" :
                System.exit(0);
        }
    }
}
