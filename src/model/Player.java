package model;

/**
 * La classe Player représente un joueur dans le jeu. Un joueur possède un nom, un nom de famille,
 * un genre, un nom de ferme, un niveau d'énergie, un niveau de santé et un inventaire.
 */
public class Player {
	private String name;
	private String lastName;
	private Gender gender;
	private String skin;
	private String nameFarm;
	private double energy;
	private double health;
	private Inventory inventory;

	/**
	 * Constructeur de la classe Player.
	 * @param name Le prénom du joueur.
	 * @param lastName Le nom de famille du joueur.
	 * @param gender Le genre du joueur (Genre enum).
	 * @param skin La texture de la peau du joueur.
	 * @param nameFarm Le nom de la ferme du joueur.
	 * @param energy Le niveau d'énergie du joueur (double).
	 * @param health Le niveau de santé du joueur (double).
	 * @param inventory L'inventaire du joueur (objet de type Inventory).
	 */	
	public Player(String name, String lastName, Gender gender, String skin, String nameFarm, double energy, double health, Inventory inventory) {
		 this.name = name; 
		 this.lastName = lastName;
		 this.gender = gender;
		 this.skin = skin;
		 this.setNameFarm(nameFarm);
		 this.energy = energy;
		 this.health = health;
		 this.inventory = inventory;
	 }

	 /**
	  * Méthode permettant d'obtenir le nom de famille du joueur.
	  * @return le nom de famille du joueur.
	  */
	 public String getLastName() {
		 return lastName;
	 }

	 /**
	  * Méthode permettant de définir le nom de famille du joueur.
	  * @param lastName le nouveau nom de famille du joueur.
	  */
	 public void setLastName(String lastName) {
		 this.lastName = lastName;
	 }

	 /**
	  * Méthode permettant d'obtenir le nom du joueur.
	  * @return le nom du joueur.
	  */	public String getName() {
		  return name;
	  }

	  /**
	   * Méthode permettant de définir le nom du joueur.
	   * @param name le nouveau nom du joueur.
	   */
	  public void setName(String name) {
		  this.name = name;
	  }

	  /**
	   * Méthode permettant d'obtenir le genre du joueur.
	   * @return le genre du joueur.
	   */
	  public Gender getGender() {
		  return gender;
	  }

	  /**
	   * Méthode permettant de définir le genre du joueur.
	   * @param gender le nouveau genre du joueur.
	   */
	  public void setGender(Gender gender) {
		  this.gender = gender;
	  }

	  /**
	   * Méthode permettant d'obtenir le type de peau du joueur.
	   * @return le type de peau du joueur.
	   */
	  public String getSkin() {
		  return skin;
	  }

	  /**
	   * Méthode permettant de définir le type de peau du joueur.
	   * @param skin le nouveau type de peau du joueur.
	   */
	  public void setSkin(String skin) {
		  this.skin = skin;
	  }

	  /**
	   * Méthode permettant d'obtenir le nom de la ferme du joueur.
	   * @return le nom de la ferme du joueur.
	   */
	  public String getNameFarm() {
		  return nameFarm;
	  }

	  /**
	   * Méthode permettant de définir le nom de la ferme du joueur.
	   * @param nameFarm le nouveau nom de la ferme du joueur.
	   */
	  public void setNameFarm(String nameFarm) {
		  this.nameFarm = nameFarm;
	  }

	  /**
	   * Méthode permettant d'obtenir l'énergie du joueur.
	   * @return l'énergie du joueur.
	   */
	  public double getEnergy() {
		  return energy;
	  }

	  /**
	   * Méthode permettant de définir l'énergie du joueur.
	   * @param energy la nouvelle valeur de l'énergie du joueur.
	   */
	  public void setEnergy(double energy) {
		  this.energy = energy;
	  }

	  /**
	   * Méthode permettant d'obtenir la santé du joueur.
	   * @return la santé du joueur.
	   */
	  public double getHealth() {
		  return health;
	  }

	  /**
	   * Méthode permettant de définir la santé du joueur.
	   * @param health la nouvelle valeur de la santé du joueur.
	   */
	  public void setHealth(double health) {
		  this.health = health;
	  }

	  /**
	   * Méthode permettant d'obtenir l'inventaire du joueur.
	   * @return l'inventaire du joueur.
	   */
	  public Inventory getInventory() {
		  return inventory;
	  }

	  /**
	   * Méthode permettant de définir l'inventaire du joueur.
	   * @param inventory le nouvel inventaire du joueur.
	   */
	  public void setInventory(Inventory inventory) {
		  this.inventory = inventory;
	  }

}
