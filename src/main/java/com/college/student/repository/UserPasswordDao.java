package com.college.student.repository;

public interface UserPasswordDao {
    public boolean exists(String userName, String userPassword);
}
