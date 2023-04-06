package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import model.Position;

class PositionTest {
	
	@Test
	void testGetX() {
		Position pos = new Position(3, 4);
		assertEquals(3, pos.getX());
	}
	
	@Test
	void testGetY() {
		Position pos = new Position(3, 4);
		assertEquals(4, pos.getY());
	}
	
	@Test
	void testSetX() {
		Position pos = new Position(3, 4);
		pos.setX(5);
		assertEquals(5, pos.getX());
	}
	
	@Test
	void testSetY() {
		Position pos = new Position(3, 4);
		pos.setY(6);
		assertEquals(6, pos.getY());
	}

}
