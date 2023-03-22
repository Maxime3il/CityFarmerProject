package application;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Gender;
import model.Player;

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
        
    @FXML
    public void initialize() {
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
    
    private void startTimeEnergyBar() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> updateEnergyProgessBar()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
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
}