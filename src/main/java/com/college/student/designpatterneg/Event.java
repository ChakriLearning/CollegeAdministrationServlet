package com.college.student.designpatterneg;

public interface Event {
    public <T> void registerObserver(T observer);
    public <T> void unregisterObserver(T observer);
    public <T> void notifyAllObservers(T message);  //method to notify if change in its state to all the Observers
    public <T> void addNewUser(T user);
    public <T> void removeUser(T user);
    public void printLoggedInUsers();
}
