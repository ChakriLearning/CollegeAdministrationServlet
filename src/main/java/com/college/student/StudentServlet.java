package com.college.student;

import com.college.student.controller.UpdateStudentServlet;
import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStudentServlet.class);
    private final StudentService studentService = new StudentService("db");
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("invokes doPost() method at line 21-StudentServlet.java");
        response.setContentType("application/json");
        Gson gson = new Gson();
        // Read JSON data from the request body
        BufferedReader reader = request.getReader();
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonStringBuilder.append(line);
        }

        // Convert JSON data to Student object
        Student student = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        // Perform necessary operations (e.g., adding student to database)
        studentService.addStudent(student);

        // Prepare response JSON
        String jsonResponse = gson.toJson(student);

        // Send response back to client
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("invokes doPut() method at line 49-StudentServlet.java");
        response.setContentType("application/json");
        Gson gson = new Gson();
        // Read JSON data from the request body
        BufferedReader reader = request.getReader();
        StringBuilder jsonStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonStringBuilder.append(line);
        }

        // Convert JSON data to Student object
        Student student = gson.fromJson(jsonStringBuilder.toString(), Student.class);

        // Perform necessary operations (e.g., adding student to database)
        studentService.updateStudentDetailsByRollNo(student);

        // Prepare response JSON
        String jsonResponse = gson.toJson(student);

        // Send response back to client
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("invokes doDelete() method at line 76-StudentServlet.java");
        response.setContentType("application/json");
        Gson gson = new Gson();

        // Read JSON data from the request body
        BufferedReader reader = request.getReader();
        String jsonRequestBody = reader.readLine();

        // Convert JSON data to Student object
        Student student = gson.fromJson(jsonRequestBody, Student.class);

        // Delete the student by roll number and get the deleted student
        logger.debug("line 88 - before delete");
        Student deletedStudent = studentService.deleteStudentByRollNo(student.getRollNo());
        logger.debug("line 90 after successfully");
        // Convert deletedStudent object to JSON
        String jsonResponse = gson.toJson(deletedStudent);

        // Send the JSON response back to the client
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }
}
