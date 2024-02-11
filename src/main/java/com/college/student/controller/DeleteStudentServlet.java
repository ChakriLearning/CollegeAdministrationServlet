package com.college.student.controller;

import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        if (!studentService.isStudentExist(Integer.parseInt(request.getParameter("rollNo")))) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>notFound</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Student with rollNo :  " + request.getParameter("rollNo") + " <strong> - Not Found</strong><h3>");
            out.println("click below to manage students<br>");
            out.println("<br>click below : <a href='StudentChoices.html'><h2>click here<h2></a>");
            out.println("</body>");
            out.println("</html>");
        } else {
            Student student = studentService.deleteStudentByRollNo(Integer.parseInt(request.getParameter("rollNo")));
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Added</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + student.getRollNo() + "   " + student.getName() + "  " + student.getRollNo() + "  " + student.getPhoneNo() + "<h1>");
            out.println("<h1>Student Detail's Deleted Successfully</h1>");
            out.println(" click here : <a href='StudentChoices.html'><h1>Main Page<h1></a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
