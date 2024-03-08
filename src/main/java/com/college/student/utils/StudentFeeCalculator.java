package com.college.student.utils;

import com.college.student.pojo.Student;
import com.college.student.service.ExecutorServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class StudentFeeCalculator {
    private static final Logger logger = LoggerFactory.getLogger(StudentFeeCalculator.class);

    public static int calculateFee() throws InterruptedException {
        logger.info("calculate fee invokes, before thread sleep");
        Thread.sleep((long) (Math.random() * 9000) + 1000);
        logger.info("calculate fee , after thread sleep, sending random fee");
        int min = 10000;
        int max = 20000;
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static Future<Integer> setStudentFee(Student student)  {
//        try {
//            logger.info("setStudentFee() - before setting student fee ");
//            student.setStudentPendingFee(StudentFeeCalculator.calculateFee());
//            logger.info("setStudentFee() - student fee set {} ",student);
//        } catch (InterruptedException e) {
//            e.getMessage();
//        }
        return ExecutorServiceHandler.getExecutorServiceInstance().submit(StudentFeeCalculator::calculateFee);
    }
}
