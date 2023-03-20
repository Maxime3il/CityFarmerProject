package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    
    public Inventory() {
        items = new ArrayList<>();
        items.add(new Item("carotte", 3, 5));
        items.add(new Item("porc", 2, 5));
        items.add(new Item("lait", 7, 10));
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
}
