package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
        stage.close();
    }
	
    @FXML
    private Label LabelEnergy;

    @FXML
    private Label labelGenre;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelPrenom;

    @FXML
    private Label labelVie;
    
    @FXML
    private ProgressBar healthProgressBar;
    
    @FXML
    private ProgressBar energyProgressBar;

    private Timeline timeline;    
    
    private double progressValueEnergy = 1.0;
    
    private double progressValueHealth = 1.0;
    
    @FXML
    public void initialize() {
    	//TODO A MODIFIER APRES LE CHOIX DU PERSONNAGE 
    	labelNom.setText("Barbaste");
    	labelPrenom.setText("Maxime");
    	labelGenre.setText("Homme");
    	Player playerBouchon = new Player(labelNom.getText(), labelPrenom.getText(), Gender.MALE, progressValueEnergy, progressValueHealth, null);
    	System.out.println(playerBouchon.getEnergy());
    	energyProgressBar.setProgress(playerBouchon.getEnergy());
        healthProgressBar.setProgress(playerBouchon.getHealth());
        startTimeEnergyBar();
    }
    
    private void startTimeEnergyBar() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> updateEnergyProgessBar()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    
    private void updateEnergyProgessBar() {
    	progressValueEnergy -= 0.1;
        energyProgressBar.setProgress(progressValueEnergy);
        if (progressValueEnergy <= 0.0) {
            timeline.stop();
        }
    }
}