package com.college.student.event;

import com.college.student.listener.Listener;
import com.college.student.listener.concreteclass.DeleteStudentEventListener;
import com.college.student.pojo.Student;

public class DeleteStudentEvent {
    private Student student;
    private Listener listener = new DeleteStudentEventListener();
    public DeleteStudentEvent(Student student) {
        this.student = student;
    }
    public void onEvent() {
        listener.onEvent(student);
    }
}
