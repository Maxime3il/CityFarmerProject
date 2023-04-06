/**
*	La classe Game représente une partie de jeu.
*	Elle contient un joueur et son inventaire.
*/

package model;

public class Game {
	private Player player;
	private Inventory inventory;

	/**
	 * Crée une nouvelle instance de Game avec un joueur et un inventaire.
	 * @param player Le joueur associé à cette partie.
	 * @param inventory L'inventaire associé à cette partie.
	 */
	public Game(Player player, Inventory inventory) {
		this.player = player;
		this.inventory = inventory;
	}

	/**
	 * Retourne le joueur associé à cette partie.
	 * @return Le joueur associé à cette partie.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Retourne l'inventaire associé à cette partie.
	 * @return L'inventaire associé à cette partie.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}

	/**
	 * Lance le jeu.
	 * Ce code démarre le jeu.
	 */
	public void start() {
		// Code pour démarrer le jeu
	}

	/**
	 * Charge une partie précédente.
	 * Ce code permet de charger une partie précédente.
	 */
	public void load() {
		// Code pour charger une partie précédente
	}

	/**
	 * Sauvegarde la partie actuelle.
	 * Ce code permet de sauvegarder la partie actuelle.
	 */
	public void save() {
		// Code pour sauvegarder la partie actuelle
	}
}
