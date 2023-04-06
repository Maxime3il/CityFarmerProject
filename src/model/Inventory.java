package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant l'inventaire d'un joueur, contenant une liste d'items et un montant d'argent.
 * L'inventaire est initialisé avec des items par défaut et un montant d'argent de 100 par défaut.
 */
public class Inventory {
    private List<Item> items;
    
    private double argentJoueur;
    
    /**
     * Constructeur par défaut de l'inventaire, qui initialise par défaut le montant d'argent à 100.
     */
    public Inventory() {
    	setArgentJoueur(100);
        items = new ArrayList<>();
    }
    
    /**
     * Ajoute un item à l'inventaire.
     * @param item l'item à ajouter
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
    /**
     * Retire un item de l'inventaire.
     * @param item l'item à retirer
     */
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    /**
     * Renvoie la liste d'items de l'inventaire.
     * @return la liste d'items
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    /**
     * Vérifie si un item avec le nom donné est présent dans l'inventaire.
     * Si oui, retourne l'item correspondant, sinon retourne null.
     * @param name le nom de l'item à rechercher
     * @return l'item correspondant ou null
     */
    public Item contains(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Ajoute un item à l'inventaire en fonction de son nom.
     * Si l'item est déjà présent dans l'inventaire, appelle la méthode addItem() de l'item.
     * @param name le nom de l'item à ajouter
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory: ");
        for (Item item : items) {
            sb.append(item.getName()).append(", ").append(item.getCount()).append("\n");
        }
        return sb.toString();
    }
    
    /**
     * Renvoie une chaîne de caractères représentant l'inventaire sous la forme : "Inventory: nomItem1, quantité1\nnomItem2, quantité2\n..."
     * @return la chaîne de caractères représentant l'inventaire
     */
    public void addItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                item.addItem(); // appel de la méthode addItem() de l'item
                break; // on sort de la boucle dès qu'on a trouvé l'item correspondant
            }
        }
    }
    
    /**
     * Renvoie le montant d'argent du joueur.
     * @return le montant d'argent du joueur
     */
	public double getArgentJoueur() {
		return argentJoueur;
	}

	/**
     * Modifie le montant d'argent du joueur.
     * @param argentJoueur le nouveau montant d'argent du joueur
     */
	public void setArgentJoueur(double argentJoueur) {
		this.argentJoueur = argentJoueur;
	}
}
