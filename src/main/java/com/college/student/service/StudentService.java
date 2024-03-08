//StudentService class will call various method of StudentRepository to perform operations;
package com.college.student.service;

import com.college.student.pojo.ErrorResponse;
import com.college.student.pojo.Student;
import com.college.student.repository.StudentRepository;
import com.college.student.repository.factory.StudentRepositoryFactory;
import com.college.student.utils.StudentFee;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(String storageType) {
        this.studentRepository = StudentRepositoryFactory.getStudentRepositoryInstance(storageType);
    }

    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }

    public List<Student> listStudents() throws  ErrorResponse {
        List<Student> studentList = this.studentRepository.listStudents();
        for (Student student : studentList) {
            StudentFee.setStudentFee(student);
        }
        return studentList;
    }

    public Student deleteStudentByRollNo(int rollNo) {
        return this.studentRepository.deleteStudent(rollNo);
    }

    public Student updateStudentDetailsByRollNo(Student updateStudent) {
        return this.studentRepository.updateStudentByRollNo(updateStudent);
    }

    public Student getStudentByRollNo(int studentRollNo) throws ErrorResponse {
        Student student = this.studentRepository.getStudentData(studentRollNo);
        if (student != null) return StudentFee.setStudentFee(student);
        return null;
    }

    public boolean isStudentExist(int rollNo) {
        return this.studentRepository.isExist(rollNo);
    }
}
