package com.college.student.event;

import com.college.student.listener.Listener;
import com.college.student.listener.concreteclass.GetStudentEventListener;
import com.college.student.pojo.Student;

public class GetStudentEvent {
    private Student student;
    private Listener listener = new GetStudentEventListener();
    public GetStudentEvent(Student student) {
        this.student = student;
    }
    public void onEvent() {
        listener.onEvent(student);
    }
}
