package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * La classe PageInventaireController contrôle la fenêtre de l'inventaire de l'application.
 */

public class PageInventaireController {

	@FXML
	private BorderPane scene;

	/**
	 * L'image de la carotte dans l'inventaire.
	 */
	@FXML
	private ImageView carotte;

	/**
	 * Le bouton pour fermer la fenêtre de l'inventaire.
	 */
	@FXML
	private Button closeButton;

	/**
	 * Le nombre de carottes dans l'inventaire.
	 */
	@FXML
	private Label nbCarotte;

	/**
	 * Le nombre de lait dans l'inventaire.
	 */
	@FXML
	private Label nbLait;

	/**
	 * Le nombre de steaks dans l'inventaire.
	 */
	@FXML
	private Label nbSteak;

	/**
	 * L'image du steak dans l'inventaire.
	 */
	@FXML
	private ImageView steak;

	/**
	 * L'image de l'argent dans l'inventaire.
	 */
	@FXML
	private ImageView argent;

	/**
	 * Le montant d'argent dans l'inventaire.
	 */
	@FXML
	private Label argentInventaire;

	/**
	 * Initialise les valeurs affichées dans la fenêtre de l'inventaire.
	 */
	@FXML
	public void initialize() {
	    int nbPorc = PagePersonnageController.player.getInventory().contains("porc").getCount();
	    nbSteak.setText(String.valueOf(nbPorc));
	    
	    int nbCarottes = PagePersonnageController.player.getInventory().contains("carotte").getCount();
	    nbCarotte.setText(String.valueOf(nbCarottes));
	    
	    int nbLaits = PagePersonnageController.player.getInventory().contains("lait").getCount();
	    nbLait.setText(String.valueOf(nbLaits));
	    
	    double nbArgent = PagePersonnageController.player.getInventory().getArgentJoueur();
	    argentInventaire.setText(String.valueOf(nbArgent));
	    
	    currentKeyBinding();
	}

	/**
	 * Ferme la fenêtre de l'inventaire lorsque le bouton est cliqué.
	 */
	@FXML
	void close() {
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}

	/**
	 * Détermine les touches clavier entrées par l'utilisateur
	 * Si la touche correspond à une action alors elle sera exécutée.
	 */
	private void currentKeyBinding() {
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.I) {
				//Ferme la fenêtre
				close();
			}
		});	
	}


}