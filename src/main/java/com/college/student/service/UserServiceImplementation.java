package com.college.student.service;

import com.college.student.repository.UserPasswordDAO;

public class UserServiceImplementation implements UserService{
    private final UserPasswordDAO userPasswordDAO;
    public UserServiceImplementation(){
        this.userPasswordDAO = new UserPasswordDAO();
    }
    @Override
    public boolean authenticateUser(String userName, String userPassword) {
        return this.userPasswordDAO.isUserExists(userName, userPassword);
    }
}
