package com.college.student.utils;

import java.util.HashMap;
import java.util.Map;

public class CookieHolder {
   private static Map<String,String> userCookies; //cookie,userName
    public CookieHolder() {
        userCookies = new HashMap<>();
    }

    public void addCookie(String cookieValue, String cookieName) {
        userCookies.put(cookieValue,cookieName);
    }
    public String getCookieName(String cookieValue) {
        return userCookies.get(cookieValue);
    }
}
