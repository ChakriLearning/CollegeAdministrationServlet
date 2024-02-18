package com.college.student.designpatterneg;

import javax.security.auth.Subject;

public interface Observer {
    public <T> void updateObserver(T message);
}
