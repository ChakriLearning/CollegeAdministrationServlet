//StudentService class will call various method of StudentRepository to perform operations;
package com.college.student.service;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;
import com.college.student.repository.StudentRepository;
import com.college.student.repository.factory.StudentRepositoryFactory;
import com.college.student.utils.StudentFeeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    private final StudentFeeCalculator studentFeeCalculator;

    public StudentService(String storageType) {
        this.studentRepository = StudentRepositoryFactory.getStudentRepositoryInstance(storageType);
        this.studentFeeCalculator = new StudentFeeCalculator();
    }

    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }

    public List<Student> listStudents() throws ErrorResponse, InterruptedException, ExecutionException {
//        List<Student> studentList = this.studentRepository.listStudents();
//        List<Thread> threads = new LinkedList<>();
//        for (Student student : studentList) {
//            Thread thread = new Thread(() -> {
//                logger.info("new thread invoked to setTheStudentFee {}",Thread.currentThread());
//                StudentFeeCalculator.setStudentFee(student);
//            });
//            threads.add(thread);
//            thread.start();
//        }
//        for (Thread thread : threads) {
//            logger.info("From main Thread : {} will wait until {}",Thread.currentThread() ,thread);
//            thread.join();
//        }
//        return studentList;

        List<Student> studentList = this.studentRepository.listStudents();
        List<Future<Map<Integer, Double>>> futureList = new LinkedList<>();
        for (Student student : studentList) {
            logger.info("new thread invoked to setTheStudentFee {}", Thread.currentThread());
            futureList.add(studentFeeCalculator.calculateAndGetPendingFee(student));
        }
        logger.info("Job Submitted to all Threads");

        while (!futureList.isEmpty()) {
            for (Iterator<Future<Map<Integer, Double>>> iterator = futureList.iterator(); iterator.hasNext(); ) {
                Future<Map<Integer, Double>> future = iterator.next();
                if (future.isDone()) {
                    logger.info("");
                    Map<Integer, Double> feeMap = future.get();
                    Map.Entry<Integer, Double> next = feeMap.entrySet().iterator().next();
                    Integer rollNo = next.getKey();
                    Double fee = next.getValue();
                    logger.info("Fee Calculation Completed For rollNo : {}, Fee : {}", rollNo, fee);
                    if (studentFeeCalculator.setStudentPendingFee(studentList, rollNo, fee)) iterator.remove();
                }
            }

        }
        return studentList;
    }

    public Student deleteStudentByRollNo(int rollNo) {
        return this.studentRepository.deleteStudent(rollNo);
    }

    public Student updateStudentDetailsByRollNo(Student updateStudent) {
        return this.studentRepository.updateStudentByRollNo(updateStudent);
    }

    public Student getStudentByRollNo(int studentRollNo) throws ErrorResponse, InterruptedException, ExecutionException {
        Student student = this.studentRepository.getStudentData(studentRollNo);
        Future<Map<Integer, Double>> studentFeeMap = studentFeeCalculator.calculateAndGetPendingFee(student);
        Map<Integer, Double> feeMap = studentFeeMap.get();
        Map.Entry<Integer, Double> next = feeMap.entrySet().iterator().next();
        Integer rollNo = next.getKey();
        Double fee = next.getValue();
        if (student.getRollNo() == rollNo) student.setPendingFee(fee);
        return student;
    }

    public boolean isStudentExist(int rollNo) {
        return this.studentRepository.isExist(rollNo);
    }
}
