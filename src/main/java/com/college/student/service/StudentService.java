//StudentService class will call various method of StudentRepository to perform operations;
package com.college.student.service;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;
import com.college.student.repository.StudentRepository;
import com.college.student.repository.factory.StudentRepositoryFactory;
import com.college.student.utils.StudentFeeCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(String storageType) {
        this.studentRepository = StudentRepositoryFactory.getStudentRepositoryInstance(storageType);
    }

    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }

    public List<Student> listStudents() throws ErrorResponse, InterruptedException {
        List<Student> studentList = this.studentRepository.listStudents();
        List<Thread> threads = new LinkedList<>();
        for (Student student : studentList) {
            Thread thread = new Thread(() -> {
                logger.info("new thread invoked to setTheStudentFee {}",Thread.currentThread());
                StudentFeeCalculator.setStudentFee(student);
            });
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            logger.info("From main Thread : {} will wait until {}",Thread.currentThread() ,thread);
            thread.join();
        }
        return studentList;
    }

    public Student deleteStudentByRollNo(int rollNo) {
        return this.studentRepository.deleteStudent(rollNo);
    }

    public Student updateStudentDetailsByRollNo(Student updateStudent) {
        return this.studentRepository.updateStudentByRollNo(updateStudent);
    }

    public Student getStudentByRollNo(int studentRollNo) throws ErrorResponse, InterruptedException {
        Student student = this.studentRepository.getStudentData(studentRollNo);
        Thread thread = new Thread( () -> {
            StudentFeeCalculator.setStudentFee(student);
        });
        thread.start();
        thread.join();
        return student;
    }

    public boolean isStudentExist(int rollNo) {
        return this.studentRepository.isExist(rollNo);
    }
}
