package com.college.student.utils;

import jakarta.servlet.http.Cookie;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CookieHolder {
   private static Map<String,String> userCookies; //cookie,userName

    public void addCookie(String cookie, String userName) {
        userCookies.put(cookie,userName);
    }
    public String getUserName(String cookie) {
        return userCookies.get(cookie);
    }
}
