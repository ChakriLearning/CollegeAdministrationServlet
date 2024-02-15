package com.college.student.repository;

import com.college.student.utils.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserPasswordDAO {
    public boolean isUserExists(String userName, String userPassword) {
        String query = "select * from userData where userName = ? And userPassword = ?";
        boolean userExists = false;
        try {
            PreparedStatement preparedStatement = DBConnector.connect().prepareStatement(query);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,userPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userExists = true;
            }
        } catch (Exception e) {

        }
        return userExists;
    }
}
