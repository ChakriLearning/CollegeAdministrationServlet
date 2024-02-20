package com.college.student.observerpatterneg;

public interface Observer {
    public <T> void updateObserver(T message);
    public <T> void eventFired(T object);
}
