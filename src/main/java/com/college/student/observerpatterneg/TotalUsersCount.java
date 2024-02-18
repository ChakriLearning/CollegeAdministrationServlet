package com.college.student.observerpatterneg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotalUsersCount implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(TotalUsersCount.class);

    @Override
    public <T> void updateObserver(T message) {
        logger.info((String) message);
    }
}
