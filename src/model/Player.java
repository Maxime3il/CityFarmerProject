package model;

public class Player {
	private String name;
	private String lastName;
    private int age;
    private Gender gender;
    private double energy;
    private double health;
    private Inventory inventory;
    
    // Constructor
    public Player(String lastName, String name, Gender gender, double energy, double health, Inventory inventory) {
        this.name = name;
        this.gender = gender;
        this.energy = energy;
        this.health = health;
        this.inventory = inventory;
    }
    
    // Getter and Setter methods for all attributes
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    
    public double getEnergy() {
        return energy;
    }
    
    public void setEnergy(double energy) {
        this.energy = energy;
    }
    
    public double getHealth() {
        return health;
    }
    
    public void setHealth(double health) {
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
