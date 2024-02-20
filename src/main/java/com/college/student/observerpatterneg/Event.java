package com.college.student.observerpatterneg;

public interface Event {
    public <T> void registerObserver(T event,T observer);
    public <T> void unregisterObserver(T event,T observer);
    public <T> void notifyAllObservers(T message);  //method to notify if change in its state to all the Observers
    public <T> void notifyObserverForSpecificEvent(T eventType, T observer, T object);
}
