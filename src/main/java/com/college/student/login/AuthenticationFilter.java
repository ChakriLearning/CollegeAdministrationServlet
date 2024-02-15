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
    private ErrorResponse errorResponse;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    public void init(FilterConfig filterConfig) {
        cookieHolder = new CookieHolder();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
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
            logger.error("Error Occurred While Fetching Cookies");
        } catch (Exception e) {
            logger.error("Exception Occurred while Pre-Processing the Request : ", e);
            this.errorResponse = new ErrorResponse(500, "Exception Occurred while Pre-Processing the Request");
        }
        if (authenticationSuccessfull) {
            logger.error("Authentication Successful : {}", authenticationSuccessfull);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.error("Exception Occurred while Authenticating ");
            this.errorResponse = new ErrorResponse(500, "Exception Occurred while Pre-Processing the Request");
        }

        Gson gson = new Gson();
        String jsonResponse = gson.toJson(this.errorResponse);
        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = servletResponse.getWriter();
        out.println(jsonResponse);
        logger.error("Error Submitted to Response : " + jsonResponse);
    }
}
