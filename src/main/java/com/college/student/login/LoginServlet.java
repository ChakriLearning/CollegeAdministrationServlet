package com.college.student.login;

import com.college.student.pojo.ErrorResponse;
import com.college.student.service.UserService;
import com.college.student.service.impl.UserServiceImpl;
import com.college.student.utils.CookieHolder;
import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class LoginServlet extends HttpServlet {
    private final UserService userService;

    public LoginServlet() {
        this.userService = new UserServiceImpl();
    }

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Login Request Received");
        ErrorResponse errorResponse = null;
        try {
            String userName = request.getParameter("username");
            String userPassword = request.getParameter("password");

            if (this.userService.authenticateUser(userName, userPassword)) {
                String cookieValue = UUID.randomUUID().toString(); //generating random cookie
                String cookieName = "my_auth_cookie";
                Cookie cookie = new Cookie(cookieName, cookieValue);
                logger.info("random cookie generated for new user : {}", cookieValue);
                CookieHolder.addUserName(cookieValue, userName);
                logger.info("user cookie added successfully username : {} and cookie : {}", userName, cookieValue);
                response.addCookie(cookie);
                logger.info("User Cookie added Successfully to browser : {}", cookie);
                response.sendRedirect("ListStudentDataTable.html?username="+userName);
                logger.info("User Redirected to ListStudent Home Page");
                return;
            } else {
                errorResponse = new ErrorResponse(401, "Invalid UserName and Password");
                logger.error("Invalid User Found : Username : {} and User Password : {}", userName, userPassword);
            }
        } catch (Exception e) {
            errorResponse = new ErrorResponse(500, "Error - While logging : " + e.getMessage());
            logger.error("Error Occurred while trying to Login ", e);
        }
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(errorResponse);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(jsonResponse);
        out.flush();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        if (CookieHolder.isUserExists(userName)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            logger.info("User Exists with name : {}", userName);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.error("Unauthorized User Found : {}",userName);
        }
    }
}
