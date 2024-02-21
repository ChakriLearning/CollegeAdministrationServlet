package com.college.student.file;

import com.college.student.controller.StudentServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class FileServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(FileServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directoryPath = "C:\\Users\\chakr\\IdeaProjects\\CollegeAdministrationServlet\\";
        int maxFileSize = 10 * 1024 * 1024; //10MB
        String encoding = "UTF-8";
        Part part = request.getPart("screenshot");
        String fileName = part.getSubmittedFileName();
        getServletContext().setAttribute("fileName", fileName);
        String relativeFilePath = directoryPath + "\\" + fileName;
        part.write(directoryPath + fileName);
        PrintWriter out = response.getWriter();
        response.sendRedirect("DownloadFile.html");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = getServletContext().getInitParameter("fileName");

        File file = new File("C:\\Users\\chakr\\IdeaProjects\\CollegeAdministrationServlet\\");
        response.setContentType("application/octet-stream");
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}
