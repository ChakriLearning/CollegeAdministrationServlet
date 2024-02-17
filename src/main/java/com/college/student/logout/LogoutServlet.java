package com.college.student.logout;

import com.college.student.pojo.UserEntity;
import com.college.student.utils.CookieHolder;
import com.college.student.utils.HttpUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = HttpUtil.getCookieByName("my_auth_cookie", request);
        UserEntity userEntity = CookieHolder.removeUser(cookieValue);
        String userName = userEntity.getUserName();
        request.getSession(false).removeAttribute(userName);
        request.getSession(false).invalidate();
        response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        response.sendRedirect("LoginPage.html");
    }
}
