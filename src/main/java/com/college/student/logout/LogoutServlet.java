package com.college.student.logout;

import com.college.student.observerpatterneg.EventHandler;
import com.college.student.observerpatterneg.Observer;
import com.college.student.observerpatterneg.TotalUsersCount;
import com.college.student.pojo.UserEntity;
import com.college.student.utils.CookieHolder;
import com.college.student.utils.HttpUtil;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.college.student.observerpatterneg.Event;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    private Event event;

    public void init(ServletConfig servletConfig) {
        this.event = new EventHandler();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String cookieValue = HttpUtil.getCookieByName("my_auth_cookie", request);
        UserEntity userEntity = CookieHolder.removeUser(cookieValue);
        Observer observer = new TotalUsersCount();
        event.notifyObserverForSpecificEvent("countUsers", observer, userEntity);
        HttpUtil.removeClientCookieFromBrowser("my_auth_cookie", request, response);
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            httpSession.removeAttribute(cookieValue);
            httpSession.invalidate();
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("LoginPage.html");
    }
}
