package test;

import org.junit.Test;

import model.Gender;
import model.Inventory;
import model.Player;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testGettersAndSetters() {
        // Create a Player object
        Player player = new Player("John", "Doe", Gender.Homme, "light", "My Farm", 100.0, 100.0, new Inventory());

        // Test getters
        assertEquals("John", player.getName());
        assertEquals("Doe", player.getLastName());
        assertEquals(Gender.Homme, player.getGender());
        assertEquals("light", player.getSkin());
        assertEquals("My Farm", player.getNameFarm());
        assertEquals(100.0, player.getEnergy(), 0.0);
        assertEquals(100.0, player.getHealth(), 0.0);
        assertNotNull(player.getInventory());

        // Test setters
        player.setName("Jane");
        player.setLastName("Doe");
        player.setGender(Gender.Femme);
        player.setSkin("dark");
        player.setNameFarm("Our Farm");
        player.setEnergy(50.0);
        player.setHealth(75.0);
        Inventory newInventory = new Inventory();
        player.setInventory(newInventory);

        assertEquals("Jane", player.getName());
        assertEquals("Doe", player.getLastName());
        assertEquals(Gender.Femme, player.getGender());
        assertEquals("dark", player.getSkin());
        assertEquals("Our Farm", player.getNameFarm());
        assertEquals(50.0, player.getEnergy(), 0.0);
        assertEquals(75.0, player.getHealth(), 0.0);
        assertEquals(newInventory, player.getInventory());
    }
}
