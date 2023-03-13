package application;

/**
 * Classe principale qui represente le point d'entree du jeu 
 * @author Iulian GAINAR
 */
public class Game {
	
	// Parametres
	private Player player;
	private World world;
	
	// Accesseurs
	public Player getPlayer() {
		return this.player;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	// Constructeurs
	public Game() {
		// le jeu cree une nouvelle instance du joueur et du monde
		this.player = new Player();
		this.world = new World();
		
		// prevoir d'autres fonctionnalites
	}
	
	// Methodes
	/**
	 * Methode qui lance la partie
	 * @author Iulian GAINAR
	 * @param 
	 */
	public void start_game() {
		// la partie se sauvegarde...
	}
	
	/**
	 * Methode qui sauvegarde la partie
	 * @author Iulian GAINAR
	 * 
	 */
	public void save_game() {
		// la partie se sauvegarde...
	}
	
	/**
	 * Methode qui charge une partie
	 * @author Iulian GAINAR
	 * 
	 */
	public void load_game() {
		// la partie se charge...
	}
}
