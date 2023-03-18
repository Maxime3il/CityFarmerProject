package model;

import java.util.List;

public class Inventory {
    private List<Item> items;
    
    public Inventory() {
    	items.add(new Item("porc", 2, 5));
    	items.add(new Item("carotte", 3, 5));
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
}
