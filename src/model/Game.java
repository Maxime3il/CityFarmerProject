package model;

public class Game {
    private Player player;
    private Inventory inventory;
    
    public Game(Player player, Inventory inventory) {
        this.player = player;
        this.inventory = inventory;
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    public Inventory getInventory() {
    	return this.inventory;
    }
    
    public void start() {
        // Code pour démarrer le jeu
    }
    
    public void load() {
        // Code pour charger une partie précédente
    }
    
    public void save() {
        // Code pour sauvegarder la partie actuelle
    }
}
