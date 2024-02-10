package com.college.student.crudservlet;

import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        List<Student> studentList = studentService.listStudents();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Student List</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> Student List <h1>");
        for (Student student : studentList) {
            out.println(student.getRollNo() + "  " + student.getName() + "  " + student.getAge() + "  " + student.getPhoneNo() + "<br>");
        }
        out.println("<a href='StudentChoices.html'><h1>Main Page</a><h1>");
        out.println("</body>");
        out.println("</html>");
    }
}
