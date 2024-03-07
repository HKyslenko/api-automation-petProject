package org.example.suits;

import org.example.tests.*;
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

}
