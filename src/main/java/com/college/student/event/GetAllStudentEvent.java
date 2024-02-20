package com.college.student.event;

import com.college.student.listener.Listener;
import com.college.student.listener.concreteclass.GetAllStudentEventListener;
import com.college.student.pojo.Student;

import java.util.List;

public class GetAllStudentEvent {
    private Listener listener = new GetAllStudentEventListener();
    private List<Student> studentList;
    public GetAllStudentEvent(List<Student> studentList) {
        this.studentList = studentList;
    }
    public void onEvent() {

    }
}
