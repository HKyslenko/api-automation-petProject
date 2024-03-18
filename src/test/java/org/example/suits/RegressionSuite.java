package org.example.suits;

import org.example.listeners.LoggingListener;
import org.example.tests.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CategoryTests.class,
        OrderTests.class,
        PetTests.class,
        TagTests.class,
        UserTests.class
})
public class RegressionSuite {
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new LoggingListener());
        core.run(RegressionSuite.class);
    }
}
