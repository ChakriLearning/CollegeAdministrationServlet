package com.college.student.login;

import com.college.student.pojo.ErrorResponse;
import com.college.student.utils.CookieHolder;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("http request and response generated {} and {}",httpServletRequest,httpServletResponse);
        Cookie[] cookiesFromRequest = httpServletRequest.getCookies();
        logger.info("Array Of Cookies got From Browser");
        CookieHolder cookieHolder = new CookieHolder();
        try {
            if (cookiesFromRequest != null) {
                for (Cookie cookie : cookiesFromRequest) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals("my_auth_cookie")) {
                        String cookieValue = cookie.getValue();
                        if (cookieHolder.getCookieName(cookieValue) != null) {
                            filterChain.doFilter(servletRequest,servletResponse);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.info("Exception Occurred while Pre-Processing the Request : ",e);
            ErrorResponse errorResponse = new ErrorResponse(500,"Exception Occurred while Pre-Processing the Request");
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(errorResponse);
            PrintWriter out = servletResponse.getWriter();
            out.println(jsonResponse);
            logger.error("Error Submitted to Response : " + jsonResponse);
        }
    }
}
