package model;

/**
 * Classe gerant les items du jeu
 * @author Iulian GAINAR
 *
 */
public class Item {
	// Parametres
    private String name;
    private int price;
    private int count;
    // Constructeurs
    public Item(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }
    
    // Accesseurs
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    } 
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    } 
    
    public void addItem() {
    	this.count += 1;
    }
    
    public void removeItem() {
    	this.count -= 1;
    }
}

