package com.college.student.controller;

import com.college.student.controller.oldfiles.UpdateStudentServlet;
import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import com.google.gson.Gson;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class StudentServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UpdateStudentServlet.class);
    private  StudentService studentService;
    public void init(ServletConfig servletConfig) {
        this.studentService = new StudentService(servletConfig.getInitParameter("storageType"));
    }
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("rollNo") != null) {
            response.setContentType("text/html");
            response.setContentType("application/json");
            //  response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            //    StudentService studentService = (StudentService) request.getServletContext().getAttribute("studentService");
            StudentService studentService = new StudentService("db");
            Student student = studentService.getStudentByRollNo(Integer.parseInt(request.getParameter("rollNo")));
            PrintWriter out = response.getWriter();
            Gson gson = new Gson();
            String stuJson = gson.toJson(student);
            out.write(stuJson);
            out.flush();
            logger.debug("invokes line 27 of getStudentdataservlet.java");
        } else {
            List<Student> studentList = studentService.listStudents();
            response.setContentType("application/json");
            //  response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
//        creating a Gson Object to convert java objects into json format
            Gson gson = new Gson();
//        creating printWriter to send the json object to client Web Browser
            PrintWriter out = response.getWriter();
//        converting list of students to json string
            String json = gson.toJson(studentList);
            out.print(json);  //writing the json object into client's web
            out.flush();
        }
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
