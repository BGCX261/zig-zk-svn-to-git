package com.twinstorm.zkskeleton.persistence;

import org.springframework.test.AbstractTransactionalSpringContextTests;

public abstract class BaseDomainTestCase extends AbstractTransactionalSpringContextTests {

  public void assertNumbersCloselyEqual(double expected, double actual, int decimalPlaces) {
    double maxDifference = 1d / (10d * decimalPlaces);
    assertTrue("Numbers not equal within " + decimalPlaces + " decimals: expected: " + expected + ", actual: " + actual, expected - actual <= maxDifference);
  }

  @Override
  protected String[] getConfigLocations() {
    return new String [] {
        "classpath:/zkskeleton-domain-test-context.xml"
    };
  }
  
}
