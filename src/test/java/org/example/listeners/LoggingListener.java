package org.example.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingListener extends RunListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void testRunStarted(Description description) {
        logger.info("Test run started");
    }

    @Override
    public void testRunFinished(Result result) {
        logger.info("Test run finished " + result.wasSuccessful());
    }

    @Override
    public void testStarted(Description description) {
        logger.info("Test started " + description.getMethodName());
    }

    @Override
    public void testFinished(Description description) {
        logger.info("Test finished " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) {
        logger.error("Test failed " + failure.getMessage());
    }
}
