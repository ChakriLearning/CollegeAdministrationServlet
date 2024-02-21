package com.college.student.listener;

import com.college.student.event.IEvent;

public interface IEventListener {
    public <Event> void onEvent(Event event);

}
