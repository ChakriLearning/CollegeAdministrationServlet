package com.college.student.event;

import com.college.student.listener.Listener;
import com.college.student.listener.concreteclass.UpdateStudentEventListener;
import com.college.student.pojo.Student;

public class UpdateStudentEvent {
    private final Student student;
    private final Listener listener = new UpdateStudentEventListener();
    public UpdateStudentEvent(Student student) {
        this.student = student;
    }
    public void onEvent() {
        listener.onEvent(student);
    }
}
