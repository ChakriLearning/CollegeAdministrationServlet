package com.college.student.controller;

import com.college.student.controller.oldfiles.UpdateStudentServlet;
import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;
import com.college.student.service.StudentService;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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
    private static final Logger logger = LoggerFactory.getLogger(StudentServlet.class);
    private StudentService studentService;

    public void init(ServletConfig servletConfig) {
        this.studentService = new StudentService(servletConfig.getInitParameter("storageType"));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        String jsonResponse;
        try {
            logger.info("Request Received to Add Student");
            BufferedReader reader = request.getReader();
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            String jsonString = jsonStringBuilder.toString();
            logger.info("Request String Received {}", jsonString);
            Student student = gson.fromJson(jsonString, Student.class);
            logger.info("Student Object Received : {}", student);
            studentService.addStudent(student);
            logger.info("Added Student to DB");
            jsonResponse = gson.toJson(student);
        } catch (Exception e) {
            logger.error("Exception Occurred while Added Student : ", e);
            ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
            jsonResponse = gson.toJson(errorResponse);
        }
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Request Received to Get the Student Details");
        String rollNo = request.getParameter("rollNo");
        String jsonResponse = null;
        Gson gson = new Gson();
        if (rollNo != null) {
            logger.info("rollNo received {}", rollNo);
            try {
                Student student = studentService.getStudentByRollNo(Integer.parseInt(rollNo));
                logger.info("Student Details Received : {}", student);
                jsonResponse = gson.toJson(student);
            } catch (Exception e) {
                logger.error("Exception Occurred while Requested to Get Student data : ", e);
                ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
                jsonResponse = gson.toJson(errorResponse);
            }
        } else {
            logger.info("Request Received to List All Students");
            try {
                List<Student> studentList = studentService.listStudents();
                logger.info("Student List Received : {}", studentList);
                jsonResponse = gson.toJson(studentList);
                logger.info("Student List Converted to json : {}", jsonResponse);
            } catch (Exception e) {
                logger.error("Exception Occurred while Requesting the to List Student Data : ", e);
                ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
                jsonResponse = gson.toJson(errorResponse);
            }
        }

        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
        logger.info("Response Successfully Generated");
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Request Received to Update the Student Data");
        Gson gson = new Gson();
        String jsonResponse = null;
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            logger.info("Request to Update the Student : {}", jsonStringBuilder);
            Student student = gson.fromJson(jsonStringBuilder.toString(), Student.class);
            student = studentService.updateStudentDetailsByRollNo(student);
            logger.info("Request Successfully Completed for Update for Student {}", student);
            jsonResponse = gson.toJson(student);
            logger.info("Generated the Json Response : {}", jsonResponse);
        } catch (Exception e) {
            logger.error("Exception Occurred while Updating the StudentByRollNo : ", e);
            ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
            jsonResponse = gson.toJson(errorResponse);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
        logger.info("Request for Update Student Successfully Completed");
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, JsonSyntaxException {
        logger.info("Request to Delete Student Received");
        Gson gson = new Gson();
        String jsonResponse = null;
        int rollNo = Integer.MIN_VALUE;
        try {
            rollNo = Integer.parseInt(request.getParameter("rollNo"));
            logger.info("Successfully Received Student RollNo : {}", rollNo);
            Student student = studentService.deleteStudentByRollNo(rollNo);
            jsonResponse = gson.toJson(rollNo);
            logger.info("Successfully Deleted the Student : {}", student);
        } catch (Exception e) {
            logger.info("Exception Occurred while Deleting a Student having rollNo : {} and Exception : ", rollNo, e);
            ErrorResponse errorResponse = new ErrorResponse(500, e.getMessage());
            jsonResponse = gson.toJson(errorResponse);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
    }
}
