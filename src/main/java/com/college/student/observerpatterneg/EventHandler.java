package com.college.student.observerpatterneg;

import com.college.student.pojo.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class EventHandler implements Event {
    private final List<Observer> listOfObservers;
    private final List<UserEntity> userEntityList;

    public EventHandler() {
        this.listOfObservers = new ArrayList<>();
        this.userEntityList = new ArrayList<>();
    }

    @Override  //method to register the observer
    public <T> void registerObserver(T observer) {
        if (observer == null) throw new NullPointerException("Null Observer");
        synchronized (EventHandler.class) {
            if (!this.listOfObservers.contains((Observer) observer)) this.listOfObservers.add((Observer) observer);
        }
    }

    @Override
    public <T> void unregisterObserver(T observer) {
        if (observer == null) throw new NullPointerException("Null Observer");
        synchronized (EventHandler.class) {
            this.listOfObservers.remove((Observer) observer);
        }
    }

    @Override
    public <T> void notifyAllObservers(T message) {
        List<Observer> observers = null;
        synchronized (EventHandler.class) {
            observers = new ArrayList<>(this.listOfObservers);
            for (Observer observer : observers) {
                observer.updateObserver(message);
            }
        }
    }

    @Override
    public <T> void addNewUser(T user) {
        if (!this.userEntityList.contains((UserEntity) user)) {
            this.userEntityList.add((UserEntity) user);
        }
    }

    @Override
    public <T> void removeUser(T user) {
        this.userEntityList.remove((UserEntity) user);
    }

    @Override
    public void printLoggedInUsers() {
        notifyAllObservers("Total No Of Users are : " + this.userEntityList.size());
        StringBuilder stringBuilder = new StringBuilder();
        for (UserEntity userEntity : this.userEntityList) {
            stringBuilder.append(userEntity.getUserName()).append(", ");
        }
        notifyAllObservers("Current LoggedIn Users Are : " + stringBuilder);
    }
}
