package com.college.student.login;

import com.college.student.controller.StudentServlet;
import com.college.student.controller.oldfiles.ListStudentServlet;
import com.college.student.pojo.ErrorResponse;
import com.college.student.service.UserService;
import com.college.student.service.UserServiceImplementation;
import com.college.student.utils.CookieHolder;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private UserService userService;
    public LoginServlet() {
        this.userService = new UserServiceImplementation();
    }
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Login Request Received");

        try {
            String userName = request.getParameter("username");
            String userPassword = request.getParameter("password");

            if (this.userService.authenticateUser(userName,userPassword)) {
                String cookieValue = UUID.randomUUID().toString(); //generating random cookie
                String cookieName = "my_auth_cookie";
                Cookie cookie = new Cookie(cookieName,cookieValue);
                logger.info("random cookie generated for new user : {}",cookieValue);
                CookieHolder cookieHolder = new CookieHolder();
                cookieHolder.addCookie(cookieValue,userName);
                logger.info("user cookie added successfully username : {} and cookie : {}",userName,cookieValue);
                response.addCookie(cookie);
                logger.info("User Cookie added Successfully to browser : {}",cookie);
                response.sendRedirect("ListStudentDataTable.html");
                logger.info("User Redirected to ListStudent Home Page");
            } else {
                ErrorResponse errorResponse = new ErrorResponse(401,"Invalid User");
                logger.error("Invalid User Found : Username : {} and User Password : {}",userName,userPassword);
                PrintWriter out = response.getWriter();
                out.println(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(500,"Error - While logging");
            logger.error("Error Occurred while trying to Login ",e);
        }
    }
}
