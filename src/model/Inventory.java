package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    
    private double argentJoueur;
    
    public Inventory() {
    	setArgentJoueur(100);
        items = new ArrayList<>();
        items.add(new Item("carotte", 3, 5));
        items.add(new Item("porc", 2, 5));
        items.add(new Item("lait", 7, 10));
    }
    
    /**
     * Initialisation d'un inventaire utile pour le marchand de la partie
     * @param nbrSteak
     * @param nbrCarotte
     * @param nbrLait
     */
    public Inventory(int nbrSteak, int nbrCarotte, int nbrLait) {
        items = new ArrayList<>();
        items.add(new Item("carotte", 5, nbrCarotte));
        items.add(new Item("porc", 4, nbrSteak));
        items.add(new Item("lait", 10, nbrLait));
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public Item contains(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory: ");
        for (Item item : items) {
            sb.append(item.getName()).append(", ").append(item.getCount()).append("\n");
        }
        return sb.toString();
    }
    
    public void addItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.addItem(); // appel de la méthode addItem() de l'item
                break; // on sort de la boucle dès qu'on a trouvé l'item correspondant
            }
        }
    }

	public double getArgentJoueur() {
		return argentJoueur;
	}

	public void setArgentJoueur(double argentJoueur) {
		this.argentJoueur = argentJoueur;
	}
    
    
}
