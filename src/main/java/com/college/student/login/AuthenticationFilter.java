package com.college.student.login;

import com.college.student.pojo.ErrorResponse;
import com.college.student.utils.CookieHolder;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("http request and response generated {} and {}",httpServletRequest,httpServletResponse);
        Cookie[] userCookie = httpServletRequest.getCookies();
        logger.info("Array Of Cookies got From Browser");
        CookieHolder cookieHolder = new CookieHolder();
        try {
            if (userCookie != null) {
                for (Cookie cookie : userCookie) {
                    String userName = cookie.getName();
                    String cookieValue = cookie.getValue();
                    if (userName.equals(cookieHolder.getUserName(cookieValue))) {
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("Exception Occurred while Pre-Processing the Request : ",e);
            ErrorResponse errorResponse = new ErrorResponse(500,"Exception Occurred while Pre-Processing the Request");
        }
    }
}
