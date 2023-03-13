package modele;

public class Player {
	private String name;
    private int age;
    private Gender gender;
    private int energy;
    private int health;
    private Inventory inventory;
    
    // Constructor
    public Player(String name, int age, Gender gender, int energy, int health, Inventory inventory) {
        this.name = name;
        this.age = age;
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
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
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
        if (inventory.contains(item)) {
            // Use the item
            item.use();
            
            // Remove the item from the inventory
            inventory.remove(item);
        } else {
            System.out.println("You don't have this item in your inventory.");
        }
    }
}
