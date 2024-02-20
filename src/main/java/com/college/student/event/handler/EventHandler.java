package com.college.student.event.handler;

import com.college.student.event.AddStudentEvent;
import com.college.student.event.DeleteStudentEvent;
import com.college.student.event.GetStudentEvent;
import com.college.student.event.UpdateStudentEvent;
import com.college.student.listener.Listener;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static final List<Listener> listenerList = new ArrayList<>();



    public void registerListener(Listener listener) {
        if (!listenerList.contains(listener)) {
            listenerList.add(listener);
        }

    }

    public void unRegisterListener(Listener listener) {
        listenerList.remove(listener);
    }


    public void publishEvent(AddStudentEvent event) {
        event.onEvent();
    }

    public void publishEvent(DeleteStudentEvent event) {
        event.onEvent();
    }

    public void publishEvent(UpdateStudentEvent event) {
        event.onEvent();
    }

    public void publishEvent(GetStudentEvent event) {
        event.onEvent();
    }
    

}
