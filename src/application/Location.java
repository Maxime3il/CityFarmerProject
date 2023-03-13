package application;

/**
 * Classe gerant la location (lieu) de jeu
 * @author Iulian GAINAR	
 *
 */
public class Location {
	// Parametres
	private String name;
	private String description;
	private List<Item> items;
	
	// Accesseurs
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description
	}
	
	public String setDescription(String description) {
		this.description = description;
	}
	
	public List<Item> getItems (){
		return this.items;
	}
	
	// Constructeurs
	public Location(String name, String description, List<Item> items) {
		this.name = name;
		this.description = description;
		this.items = items;
	}
	// Methodes
}
