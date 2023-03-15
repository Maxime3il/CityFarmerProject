package model;

public class Player {
	private String name;
    private Gender gender;
    private int energy;
    private int health;
    private Inventory inventory;
    
    // Constructor
    public Player(String name, Gender gender, int energy, int health, Inventory inventory) {
        this.name = name;
        this.gender = gender;
        this.energy = energy;
        this.health = health;
        this.inventory = inventory;
    }
    
    // Getter and Setter methods for all attributes
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public int getEnergy() {
        return energy;
    }
    
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int health) {
        this.health = health;
    }
    
    public Inventory getInventory() {
        return inventory;
    }
    
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    // Sleep method to increase energy
    public void sleep() {
        energy += 10;
    }
    
    // Interact method to use items from inventory
    public void interact(Item item) {
    	//TODO prochainement
    }
}
