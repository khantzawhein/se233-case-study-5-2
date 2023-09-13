package com.se233.chapter5_tdd;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SelectClasses({GameLoopTest.class, SnakeTest.class})
@Suite
public class JUnitTestSuite {
}
