package com.college.student.event.handler;

import com.college.student.event.IEvent;
import com.college.student.listener.IEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventHandler {
    private static final Map<Class<? extends IEvent>, List<IEventListener<? extends IEvent>>> listeners = new HashMap<>();  // "?" is a wildcard means anytype here anytype of class or child class of IEvent can be accepted; // for specific eventType we have a listOf Listeners;
    private static volatile EventHandler instance;
    private static final BlockingQueue<IEvent> iEventQueue = new LinkedBlockingQueue<>();
    private boolean sync;
    private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);

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

    public void publishEvent(IEvent event, boolean sync) throws InterruptedException {
        logger.info("New Event Started to Publish");
        this.sync = sync;
        iEventQueue.put(event);
        logger.info("Event added to Queue in PublishEvent()");
    }


    public static void invokeListeners() throws InterruptedException {
        logger.info("Listeners waiting for an event just before while loop");
        while (true) {
            logger.info("from invokeListeners(), Listeners entered into loop, and are waiting for an Event");
            IEvent event = iEventQueue.take(); // Wait for an event to be available
            logger.info("A new Event Occurred to Listen");
            List<IEventListener<? extends IEvent>> listenerList = listeners.get(event.getClass());
            if (listenerList != null) {
                for (IEventListener<? extends IEvent> iEventListener : listenerList) {
                    if (getInstance().sync) {
                        notifyListeners(event, iEventListener);
                    } else {
                        new Thread(() -> notifyListeners(event, iEventListener)).start();
                    }
                }
            }
        }
    }


    private static void notifyListeners(IEvent event, IEventListener listener) {
        listener.onEvent(event);
    }

}
