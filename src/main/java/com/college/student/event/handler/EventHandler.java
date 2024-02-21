package com.college.student.event.handler;

import com.college.student.event.IEvent;
import com.college.student.listener.IEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
    private static final Map<Class<? extends IEvent>, List<IEventListener<? extends IEvent>>> listeners = new HashMap<>();  // "?" is a wildcard means anytype here anytype of class or child class of IEvent can be accepted; // for specific eventType we have a listOf Listeners;
    private static volatile EventHandler instance;

    public EventHandler() {

    }

    public static EventHandler getInstance() {
        if (instance == null) {
            synchronized (EventHandler.class) {
                if (instance == null) {
                    instance = new EventHandler();
                }
            }
        }
        return instance;
    }

    public void registerListener(Class<? extends IEvent> eventType, IEventListener<? extends IEvent> listener) {
//        List<IEventListener> iEventListeners = listeners.getOrDefault(eventType, new ArrayList<>());  //retrieves the value of eventType(key) if key is exists else it returns the default (new ArrayList<>)
//        iEventListeners.add(listener);  //add the specific listener to the list of eventListeners and
//        listeners.put(eventType, iEventListeners);
        List<IEventListener<? extends IEvent>> iEventListeners = listeners.get(eventType);
        if (iEventListeners == null) {
            iEventListeners = new ArrayList<>();
            listeners.put(eventType, iEventListeners);
        }
        iEventListeners.add(listener);
    }

    public void unRegisterListener(Class<? extends IEvent> eventType, IEventListener<? extends IEvent> listener) {
//        //this method retrieves the value(here List<IEventListener>) of eventType, and we removed the specific listener from that list;
//        listeners.getOrDefault(eventType, new ArrayList<>()).remove(listener);
        List<IEventListener<? extends IEvent>> iEventListeners = listeners.get(eventType);
        if (iEventListeners != null) {
            iEventListeners.remove(listener);
        }
    }

    public void publishEvent(IEvent event, boolean sync) {

        List<IEventListener<? extends IEvent>> listenerList = listeners.get(event.getClass());  //getClass() returns runtime class of the event obj suppose IEvent event = new AddStudentEventListener() then it'll return the AddStudentEventListener class;
        if (listenerList != null) {
            for (IEventListener<? extends IEvent> iEventListener : listenerList) {
                if (sync) {
                    synchronized (this) {
                        new Thread(() -> notifyListeners(event, iEventListener)).start();
                    }
                } else {
                    notifyListeners(event, iEventListener);
                }
            }
        }
    }

    public  void notifyListeners(IEvent event, IEventListener iEventListener) {
        iEventListener.onEvent(event);
    }

}
