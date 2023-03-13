package application;

/**
 * Classe gerant un item de jeu
 * @author Iulian GAINAR
 *
 */
public class Item {
	
	// Parametres
	private String name;
	private String description;
	private float price;
	private int quality;

	// Accesseurs
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return this.price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuality() {
		return this.quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	
	// Constructeurs
	public Item(String name, String descritption, float price, int quality) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.quality = quality;
	}
	
	// Methodes
}
