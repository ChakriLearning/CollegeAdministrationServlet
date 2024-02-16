package com.college.student.logging;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LoggingFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        StringBuffer userURL = httpServletRequest.getRequestURL();
        Cookie[] userCookies = httpServletRequest.getCookies();
        String userContextPath = httpServletRequest.getContextPath();
        filterConfig.getServletContext().log("Logging Filter Invoked ");
        filterConfig.getServletContext().log(userContextPath + "  " + userURL );
    }
}
