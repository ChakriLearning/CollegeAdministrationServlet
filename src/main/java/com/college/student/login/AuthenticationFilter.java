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
    private CookieHolder cookieHolder;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig filterConfig) {
        cookieHolder = new CookieHolder();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter out = servletResponse.getWriter();
        String jsonResponse = null;
        boolean authenticationSuccessfull = false;
        logger.info("http request and response generated {} and {}", httpServletRequest, httpServletResponse);
        Cookie[] cookiesFromRequest = ((HttpServletRequest) servletRequest).getCookies();
        logger.info("Array Of Cookies got From Browser");
        try {
            if (cookiesFromRequest != null) {
                for (Cookie cookie : cookiesFromRequest) {
                    String cookieName = cookie.getName();
                    if (cookieName.equals("my_auth_cookie")) {
                        String cookieValue = cookie.getValue();
                        if (cookieHolder.getCookieName(cookieValue) != null) {
                            authenticationSuccessfull = true;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Exception Occurred while Pre-Processing the Request : ", e);
            ErrorResponse errorResponse = new ErrorResponse(401, "Exception Occurred while Pre-Processing the Request" + e.getMessage());
            jsonResponse = gson.toJson(errorResponse);
            out.println(jsonResponse);
            logger.info("Generated Pre-Processing Json Response {}",jsonResponse);
        }
        if (authenticationSuccessfull) {
            logger.info("Authentication Successful : {}", authenticationSuccessfull);
            logger.info("line before doFilter call");
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("line after doFilter call");
        } else {
            logger.error("Exception Occurred while Authenticating ");
            ErrorResponse errorResponse = new ErrorResponse(401, "Exception Occurred and Authentication Failed");
            jsonResponse = gson.toJson(errorResponse);
            out.println(jsonResponse);
            logger.info("Generated Json Error Response {}",jsonResponse);
        }
    }
}
