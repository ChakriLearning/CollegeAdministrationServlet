package com.college.student.service;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface StudentService {
    public void addStudent(Student student);
    public List<Student> listStudents()  throws ErrorResponse, InterruptedException, ExecutionException;
    public Student deleteStudentByRollNo(int rollNo);
    public Student updateStudentDetailsByRollNo(Student updateStudent);
    public Student getStudentByRollNo(int studentRollNo) throws ErrorResponse, InterruptedException, ExecutionException;
    public boolean isStudentExist(int rollNo);
}
