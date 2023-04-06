package application;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class PageInformationPersonnageController {
	@FXML
	private Button closeButton;
	@FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.hide();
    }
	
	@FXML
	private BorderPane scene;

    @FXML
    private Label labelEnergy;

    @FXML
    private Label labelGenre;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrenom;
    
    @FXML
    private Label labelNomFerme;

    @FXML
    private Label labelVie;
    
    @FXML
    private ProgressBar healthProgressBar;
    
    @FXML
    private ProgressBar energyProgressBar;

    public static Timeline timeline;  
    
    private double progressValueEnergy = PagePersonnageController.player.getEnergy();
        
    @FXML
    private ImageView persoChoisi;
        
    @FXML
    public void initialize() {
    	currentKeyBinding();
    	Image image = new Image(getClass().getResourceAsStream(PagePersonnageController.player.getSkin()));
    	persoChoisi.setImage(image);
    	labelNomFerme.setText(PagePersonnageController.player.getNameFarm());
    	labelNom.setText(PagePersonnageController.player.getLastName());
    	labelPrenom.setText(PagePersonnageController.player.getName());
    	labelGenre.setText(PagePersonnageController.player.getGender() + "");
    	energyProgressBar.setProgress(PagePersonnageController.player.getEnergy());
        healthProgressBar.setProgress(PagePersonnageController.player.getHealth());
        startTimeEnergyBar();
    }

    /**
     * Débute le timer de l'energie et modifie celle ci toutes les 3 secondes
     */
	private void startTimeEnergyBar() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> updateEnergyProgessBar()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
	/**
	 * Update l'energie et décrémente la variable locale
	 * Lorsque la barre est vide cad à 0 alors
	 * une alerte est déclaré et reset la barre d'energie à 1
	 */
    private void updateEnergyProgessBar() {
    	progressValueEnergy -= 0.01;
    	PagePersonnageController.player.setEnergy(progressValueEnergy);
        energyProgressBar.setProgress(progressValueEnergy);
        if (progressValueEnergy <= 0.0) {
        	timeline.stop();
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Personnage épuisé");
                alert.setContentText("Le personnage est épuisé cliqué sur OK pour vous reposer");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                	PagePersonnageController.player.setEnergy(1);
                }
            });
        }
    }
    
    /**
     * Détermine les touches claviers entrées par l'utilisateur
     * Si la touche correspond à une action alors elle sera exécutée.
     */
	private void currentKeyBinding(){
		scene.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.A) {
				//Ferme la fenetre
				close();
			}
		});
	}
}
