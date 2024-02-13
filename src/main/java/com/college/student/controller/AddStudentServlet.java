package com.college.student.controller;

import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AddStudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService("db");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
}
