//package com.college.student.fileupload;
//
//import com.oreilly.servlet.MultipartRequest;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/upload")
//public class FileUploadServlet extends HttpServlet {
//    public void doPost(HttpServletRequest request, HttpServletResponse response) {
//        String directoryPath = "C:/Users/chakr/IdeaProjects/CollegeAdministrationServlet";
//        int maxFileSize = 10 * 1024 * 1024;
//        String encoding = "UTF-8";
//        MultipartRequest multipartRequest = new MultipartRequest(request, directoryPath, maxFileSize,encoding);
//        multipartRequest.getFile("file");
//
//    }
//}
