package com.college.student.file;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class FileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String directoryPath = "C:\\Users\\chakr\\IdeaProjects\\CollegeAdministrationServlet";
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
        File file = new File("C:\\Users\\chakr\\IdeaProjects\\CollegeAdministrationServlet\\" + getServletContext().getInitParameter("fileName"));
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
