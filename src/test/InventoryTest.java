package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Inventory;
import model.Item;

public class InventoryTest {
	private Inventory inventory;
	private Item item1;
	private Item item2;

	@Before
	public void setUp() {
		inventory = new Inventory();
		item1 = new Item("Potion", 10, 5);
		item2 = new Item("Elixir", 20, 10);
		inventory.addItem(item1);
		inventory.addItem(item2);
	}

	@Test
	public void testAddItem() {
		Item item3 = new Item("Ether", 30, 3);
		inventory.addItem(item3);
		List<Item> items = inventory.getItems();
		assertEquals(3, items.size());
		assertEquals(item3, items.get(2));
	}

	@Test
	public void testRemoveItem() {
		Item item3 = new Item("Pomme", 10, 5);
		inventory.removeItem(item2);
		inventory.addItem(item3);
		List<Item> items = inventory.getItems();
		assertEquals(2, items.size());
		assertEquals(item1, items.get(0));
	}

	@Test
	public void testContains() {
		Item item = inventory.contains("Potion");
		assertNotNull(item);
		assertEquals(item1, item);
		item = inventory.contains("Elixir");
		assertNotNull(item);
		assertEquals(item2, item);
		item = inventory.contains("Ether");
		assertNull(item);
	}

	@Test
	public void testToString() {
		String expected = "Inventory: Potion, 5\nElixir, 10\n";
		String actual = inventory.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testAddItemByName() {
		inventory.addItem("Pomme");
		inventory.addItem("Elixir");
		inventory.addItem("Impossible");
		List<Item> items = inventory.getItems();
		//Si seulement 2 comme size alors le dernier ne s'est pas ajouté car introuvable
		assertEquals(2, items.size());
		assertEquals(5, items.get(0).getCount());
		assertEquals(11, items.get(1).getCount());
	}

	@Test
	public void testGetArgentJoueur() {
		assertEquals(100, inventory.getArgentJoueur(), 0);
	}

	@Test
	public void testSetArgentJoueur() {
		inventory.setArgentJoueur(200);
		assertEquals(200, inventory.getArgentJoueur(), 0);
	}
}
