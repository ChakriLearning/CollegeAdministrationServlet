package com.college.student.crudservlet;

import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class AddStudentServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
        int studentRollNo = Integer.parseInt((String) request.getParameter("rollNo"));
        String studentName = (String) request.getParameter("name");
        byte studentAge = Byte.parseByte((String) request.getParameter("age"));
        long studentPhoneNo = Long.parseLong((String) request.getParameter("phoneNo"));
        Student student = new Student();
        student.setRollNo(studentRollNo);
        student.setName(studentName);
        student.setAge(studentAge);
        student.setPhoneNo(studentPhoneNo);
        studentService.addStudent(student);
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Added</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + studentRollNo + "   " + studentName + "  " + studentAge + "  " + studentPhoneNo + "<h1>");
        out.println("<h1>Student Detail's Added Successfully</h1>");
        out.println("<a href='StudentChoices.html'>Main Page</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
