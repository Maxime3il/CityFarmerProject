package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Inventory;
import model.Item;

public class PageInventaireController {

	@FXML
	private BorderPane scene;
	
    @FXML
    private ImageView carotte;

    @FXML
    private Button closeButton;

    @FXML
    private Label nbCarotte;

    @FXML
    private Label nbLait;

    @FXML
    private Label nbSteak;

    @FXML
    private ImageView steak;
    
    @FXML
    private ImageView argent;

    @FXML
    private Label argentInventaire;

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

    @FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    /**
     * Détermine les touches claviers entrées par l'utilisateur
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
