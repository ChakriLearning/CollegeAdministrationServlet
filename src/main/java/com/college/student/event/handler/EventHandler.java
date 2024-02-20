package com.college.student.event.handler;

import com.college.student.event.*;
import com.college.student.listener.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void publishEvent(GetAllStudentEvent event) {

    }

}
