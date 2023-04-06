/**
*	Contrôleur de la page d'information du personnage
*/
package application;
import java.util.Optional;
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
	
	/**
	 * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton Fermer.
	 * Elle ferme la fenêtre en cours.
	 */
	@FXML
    void close() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.hide();
    }
	
	@FXML
	private BorderPane scene;

    @FXML
    private Label LabelEnergy;

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

    static Timeline timeline;  
    
    private double progressValueEnergy = PagePersonnageController.player.getEnergy();
        
    @FXML
    private ImageView persoChoisi;
        
    /**
     * Initialise les éléments de la page d'information.
     */
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
     * Démarre le timer de la barre d'énergie et met à jour celle-ci toutes les 3 secondes.
     */
	private void startTimeEnergyBar() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> updateEnergyProgessBar()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
	/**
	 * Met à jour la barre d'énergie et décrémente la variable locale.
	 * Si la barre d'énergie est vide, une alerte est affichée et la barre d'énergie est réinitialisée à 1.
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
