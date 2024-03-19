package com.college.student.utils;

import com.college.student.pojo.Student;
import com.college.student.service.impl.ExecutorServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

public class StudentFeeCalculator {
    private static final Logger logger = LoggerFactory.getLogger(StudentFeeCalculator.class);

    public Future<Map<Integer, Double>> calculateAndGetPendingFee(final Student student) {
        return ExecutorServiceHandler.getExecutorServiceInstance().submit(() -> {
            logger.info("calculate fee invokes, before thread sleep, Student rollNo : {}", student.getRollNo());
            Thread.sleep((long) (Math.random() * 9000) + 1000);
            logger.info("calculate fee , after thread sleep, sending random fee");
            int min = 10000;
            int max = 20000;
            Map<Integer, Double> studentVSFee = new HashMap<>();
            Double fee = (Math.random() * (max - min + 1)) + min;
            studentVSFee.put(student.getRollNo(), fee);
            return studentVSFee;
        });
    }

}
