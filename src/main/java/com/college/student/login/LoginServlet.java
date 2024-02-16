package com.college.student.login;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.UserEntity;
import com.college.student.service.UserService;
import com.college.student.service.impl.UserServiceImpl;
import com.college.student.utils.CookieHolder;
import com.college.student.utils.HttpUtil;
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
                CookieHolder.addUserName(cookieValue, new UserEntity(userName));
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = HttpUtil.getCookieByName("my_auth_cookie", request);
        logger.info("Value for my_auth_cookie : {}", cookieValue);
        if (cookieValue != null && CookieHolder.getUserEntity(cookieValue) != null) {
            logger.info("Authentication Successfully before proceeding chain.doFilter()");
            UserEntity userEntity = CookieHolder.getUserEntity(cookieValue);
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(userEntity);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(jsonResponse);
            out.flush();
        } else {
            ErrorResponse errorResponse = new ErrorResponse(401, "Invalid Login ");
            logger.error("Invalid Login");
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(errorResponse);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(jsonResponse);
            out.flush();
        }
    }
}
