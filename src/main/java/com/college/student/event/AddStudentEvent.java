package com.college.student.event;

import com.college.student.listener.Listener;
import com.college.student.listener.concreteclass.AddStudentEventListener;
import com.college.student.pojo.Student;

public class AddStudentEvent {
    private final Student student;
    private final Listener listener = new AddStudentEventListener();
    public AddStudentEvent(Student student) {
        this.student = student;
    }
    public void onEvent() {
        listener.onEvent(student);
    }
}
