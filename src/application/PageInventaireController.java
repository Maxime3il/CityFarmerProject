/**
 * Le contrôleur pour la page d'inventaire de l'application.
 */

package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PageInventaireController {

	@FXML
	private BorderPane scene;
	
	 /**
     * L'image de la carotte dans l'inventaire.
     */
    @FXML
    private ImageView carotte;

    /**
     * Le bouton de fermeture de la page.
     */
    @FXML
    private Button closeButton;

    /**
     * L'étiquette pour le nombre de carottes dans l'inventaire.
     */
    @FXML
    private Label nbCarotte;

    /**
     * L'étiquette pour le nombre de laits dans l'inventaire.
     */
    @FXML
    private Label nbLait;


    /**
     * L'étiquette pour le nombre de steaks dans l'inventaire.
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
     * L'étiquette pour la quantité d'argent dans l'inventaire.
     */
    @FXML
    private Label argentInventaire;

    /**
     * Initialise les informations de l'inventaire du joueur et configure les touches clavier.
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
     * Ferme la page d'inventaire.
     */
    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Configure les touches clavier pour interagir avec la page d'inventaire.
     * Si la touche correspond à une action alors elle sera exécutée.
     */
	private void currentKeyBinding() {
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.I) {
				//Ferme la fenetre
				close();
			}
		});	
	}

}
