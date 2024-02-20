package com.college.student.observerpatterneg;

import com.college.student.pojo.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TotalUsersCount implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(TotalUsersCount.class);
    private static final List<UserEntity> userEntityList = new ArrayList<>();

    @Override
    public <T> void updateObserver(T message) {
        logger.info((String) message);
    }

    @Override
    public <T> void eventFired(T object) {
        if (userEntityList.contains((UserEntity) object)) {
            removeUser(object);
        }
        addUser(object);
        logger.info("Total No of Users now are : {}", userEntityList.size());
        StringBuilder sb = new StringBuilder();
        for (UserEntity userEntity : userEntityList) {
            sb.append(userEntity.getUserName()).append(", ");
        }
        logger.info("Total User Name are : {}", sb);
    }

    public <T> void addUser(T user) {
        if (!userEntityList.contains((UserEntity) user)) {
            userEntityList.add((UserEntity) user);
        }
        eventFired(user);
    }

    public <T> void removeUser(T user) {
        userEntityList.remove((UserEntity) user);
        eventFired(user);
    }
}
