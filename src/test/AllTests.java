package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ InventoryTest.class, ItemTest.class, PlayerTest.class, PositionTest.class })
public class AllTests {
}
