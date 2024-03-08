package com.college.student.utils;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;
import com.college.student.service.ExecutorServiceHandler;
import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StudentFee {

    public static int calculateFee() {
        int min = 10000;
        int max = 20000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }
    public static Student setStudentFee(Student student) throws ErrorResponse {
        try {
            Future<Integer> future = ExecutorServiceHandler.getExecutorServiceInstance().submit(StudentFee::calculateFee);
            int pendingFee = future.get();
            student.setStudentPendingFee(pendingFee);
            return student;
        } catch (ExecutionException | InterruptedException e) {
            throw  new ErrorResponse(401,"Error Occurred While Added Student Fee");

        }
    }
}
