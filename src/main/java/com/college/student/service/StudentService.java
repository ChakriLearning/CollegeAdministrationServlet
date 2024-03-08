//StudentService class will call various method of StudentRepository to perform operations;
package com.college.student.service;

import com.college.student.pojo.Student;
import com.college.student.repository.StudentRepository;
import com.college.student.repository.factory.StudentRepositoryFactory;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(String storageType) {
        this.studentRepository = StudentRepositoryFactory.getStudentRepositoryInstance(storageType);
    }

    public void addStudent(Student student) {
        this.studentRepository.addStudent(student);
    }
    public List<Student> listStudents() {
        return this.studentRepository.listStudents();
    }

    public Student deleteStudentByRollNo(int rollNo) {
        return this.studentRepository.deleteStudent(rollNo);
    }

    public Student updateStudentDetailsByRollNo(Student updateStudent) {
       return this.studentRepository.updateStudentByRollNo(updateStudent);
    }

    public Student getStudentByRollNo(int studentRollNo) {
        return  this.studentRepository.getStudentData(studentRollNo);
    }
    public boolean isStudentExist(int rollNo) {
        return this.studentRepository.isExist(rollNo);
    }
}
