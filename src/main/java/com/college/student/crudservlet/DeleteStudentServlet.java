package com.college.student.crudservlet;

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
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        Student student = studentService.deleteStudentByRollNo(Integer.parseInt(request.getParameter("rollNo")));
        PrintWriter out = response.getWriter();
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
        out.println("<a href='StudentChoices.html'><h1>Main Page<h1></a>");
        out.println("</body>");
        out.println("</html>");
    }
}
