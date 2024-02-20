package com.college.student.observerpatterneg;

import com.college.student.pojo.UserEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler implements Event {
    private final Map<String, Observer> observers = new HashMap<>();

    @Override
    public <T> void registerObserver(T eventType, T observer) {
        if (!observers.containsValue((Observer) observer)) {
            observers.put((String) eventType, (Observer) observer);
        }

    }

    @Override
    public <T> void unregisterObserver(T eventType, T observer) {
        observers.remove((String) eventType,(Observer) observer);
    }

    @Override
    public <T> void notifyAllObservers(T message) {
        for (Map.Entry<String,Observer> entry : observers.entrySet()) {
            Observer observer = entry.getValue();
            observer.updateObserver(message);
        }
    }

    @Override
    public <T> void notifyObserverForSpecificEvent(T eventType, T observer, T object) {
        for (Map.Entry<String,Observer> entry : observers.entrySet()) {
            if (entry.getKey().equals(eventType) && entry.getValue().equals(observer)) {
                entry.getValue().eventFired(object);
            }
        }
    }
}
