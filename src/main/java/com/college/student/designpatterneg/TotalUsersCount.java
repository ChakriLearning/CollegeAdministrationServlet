package com.college.student.designpatterneg;

import com.college.student.pojo.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TotalUsersCount implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(TotalUsersCount.class);

    @Override
    public <T> void updateObserver(T message) {
        logger.info((String) message);
    }
}
