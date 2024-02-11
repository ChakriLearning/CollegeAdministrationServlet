package com.college.student.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMethod {

    public static  Logger logger = LoggerFactory.getLogger(MainMethod.class);
    public static void main(String[] args) {
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
    }
}
