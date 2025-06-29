package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLogging {
    private static final Logger logger = LoggerFactory.getLogger(AppenderLogging.class);

    public static void main(String[] args) {
        logger.debug("Debug message to both console and file");
        logger.info("Info message to both console and file");
        logger.error("Error message to both console and file");
    }
}
