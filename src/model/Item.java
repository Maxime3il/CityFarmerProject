package model;
// import String ???


/**
 * Classe gerant les items du jeu
 * @author Iulian GAINAR
 *
 */
public class Item {
	// Parametres
    private String name;
    private String description;
    private int price;
    
    // Constructeurs
    public Item(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    // Accesseurs
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    // Methodes
}

