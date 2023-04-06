package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import junit.framework.Test;
import junit.framework.TestSuite;

@Suite
@SelectClasses({ InventoryTest.class, ItemTest.class, PlayerTest.class, PositionTest.class })
public class AllTests {
}
