package com.college.student.utils;

public class StudentFeeCalculator {

    public static int calculateFee() {
        int min = 10000;
        int max = 20000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
