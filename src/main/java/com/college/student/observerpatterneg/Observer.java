package com.college.student.observerpatterneg;

public interface Observer {
    public <T> void updateObserver(T message);
}
