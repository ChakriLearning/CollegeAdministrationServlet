package com.college.student.utils;

import java.util.HashMap;
import java.util.Map;

public class CookieHolder {
    private static Map<String, String> userCookies = new HashMap<>();//cookie,userName


    public static void addUserName(String cookieValue, String userName) {
        userCookies.put(cookieValue, userName);
    }

    public static String getUserName(String cookieValue) {
        return userCookies.get(cookieValue);
    }

}
