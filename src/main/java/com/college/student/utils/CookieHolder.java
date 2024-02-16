package com.college.student.utils;

import com.college.student.pojo.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class CookieHolder {
    private static Map<String, UserEntity> userCookies = new HashMap<>();//cookie,userName

    public static void addUserName(String cookieValue, UserEntity userEntity) {
        userCookies.put(cookieValue, userEntity);
    }

    public static UserEntity getUserEntity(String cookieValue) {
        return cookieValue != null ? userCookies.get(cookieValue) : null;
    }

}
