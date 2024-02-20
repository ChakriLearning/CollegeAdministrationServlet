package com.college.student.fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class FileUploadServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directoryPath = "C:\\Users\\chakr\\IdeaProjects\\CollegeAdministrationServlet\\";
        int maxFileSize = 10 * 1024 * 1024; //10MB
        String encoding = "UTF-8";
        Part part = request.getPart("screenshot");
        String fileName = part.getSubmittedFileName();
        part.write(directoryPath);
        String relativeFilePath = directoryPath + "\\" + fileName;
        PrintWriter out = response.getWriter();

    }
}
