package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Item;

class ItemTest {

	Item item;

	@BeforeEach
	void setUp() {
	    item = new Item("Potion", 10, 5);
	}

	@Test
	void testGetName() {
	    assertEquals("Potion", item.getName());
	}

	@Test
	void testSetName() {
	    item.setName("Elixir");
	    assertEquals("Elixir", item.getName());
	}

	@Test
	void testGetPrice() {
	    assertEquals(10, item.getPrice());
	}

	@Test
	void testSetPrice() {
	    item.setPrice(20);
	    assertEquals(20, item.getPrice());
	}

	@Test
	void testGetCount() {
	    assertEquals(5, item.getCount());
	}

	@Test
	void testSetCount() {
	    item.setCount(10);
	    assertEquals(10, item.getCount());
	}

	@Test
	void testAddItem() {
	    item.addItem();
	    assertEquals(6, item.getCount());
	}

	@Test
	void testRemoveItem() {
	    item.removeItem();
	    assertEquals(4, item.getCount());
	}

}
