package model;

/**
 * La classe Item représente un objet dans le jeu, qui a un nom, un prix et une quantité disponible.
 */
public class Item {
	// Paramètres
	private String name;
	private int price;
	private int count;

	/**
	 * Constructeur de la classe Item.
	 * @param name le nom de l'objet
	 * @param price le prix de l'objet
	 * @param count la quantité disponible de l'objet
	 */
	public Item(String name, int price, int count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	/**
	 * Accesseur pour le nom de l'objet.
	 * @return le nom de l'objet
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Modifie le nom de l'objet.
	 * @param name le nouveau nom de l'objet
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Accesseur pour le prix de l'objet.
	 * @return le prix de l'objet
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Modifie le prix de l'objet.
	 * @param price le nouveau prix de l'objet
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Accesseur pour la quantité disponible de l'objet.
	 * @return la quantité disponible de l'objet
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Modifie la quantité disponible de l'objet.
	 * @param count la nouvelle quantité disponible de l'objet
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * Ajoute un objet à la quantité disponible.
	 */
	public void addItem() {
		this.count += 1;
	}
	
	/**
	 * Retire un objet à la quantité disponible.
	 */
	public void removeItem() {
		this.count -= 1;
	}
}

