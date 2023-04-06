package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class PageAccueilController implements Initializable {

	@FXML
	private BorderPane scene;

	@FXML
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	
	private String mute = "muted";		
	
	private static final Logger logger = Logger.getLogger(PageAccueilController.class.getName());

	
	/*
     * Cette fonction permet de lancer une nouvelle fen�tre � partir d'un fichier FXML.
     * @param url L'URL du fichier FXML � charger.
     */
	
	public void lancerXML(String url) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setOnCloseRequest(this::handleCloseRequest);
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene1 = new Scene(root1, 1920, 1080);
            stage.setScene(scene1);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unable to load window", e);
        }
    }

	private void handleCloseRequest(WindowEvent event) {
	    event.consume();
	}
	
	@FXML
	private Button btnJouer;

	/*
     * Cette fonction permet de rediriger l'utilisateur vers la page jouer.
     * @param event L'�v�nement d�clencheur.
     */
	
	@FXML
	private void redirectJouer(ActionEvent evt) {
		lancerXML("PagePersonnage.fxml");
	}

	@FXML
	private Slider volumeSlider;

	@FXML
	private Button couperSonMusique;

	@FXML
	private Button btnActiverDesactiverSon;

	private boolean jouerSonActif = true;

	/*
	 * Fonction activerDesactiverSon param: 
	 * return: Active ou Desactive la musique en changeant l'image selon le statut du bouton
	 */
	@FXML
	private void activerDesactiverSon(ActionEvent event) {
		jouerSonActif = !jouerSonActif;
		if (!jouerSonActif) {
			// Si le son est desactivé, on ajoute la classe CSS "muted" au bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().add(mute);
		} else {
			// Si le son est activé, on retire la classe CSS "muted" du bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().remove(mute);
		}
		MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
	}

	/*
	* Cette fonction joue un son � partir du chemin de fichier sp�cifi�.
	* @param path Le chemin de fichier du son � jouer.
	*/
	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currenKeyBinding();
		String path = new File("src/Video/TitleScreen.mp4").getAbsolutePath();
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaView.setMediaPlayer(mediaPlayer);
		mediaView.setFitWidth(1920);
		mediaView.setFitHeight(1080);
		mediaPlayer.setVolume(0.1);
		volumeSlider.setMin(0);
		volumeSlider.setMax(1);
		volumeSlider.setValue(0.1);
		volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> 
			mediaPlayer.setVolume(newValue.doubleValue())
		);
		mediaPlayer.setOnEndOfMedia(() -> 
			mediaPlayer.seek(Duration.ZERO)
		);
		couperSonMusique.setOnAction(event -> {
			if (mediaPlayer.isMute()) {
				mediaPlayer.setMute(false);
				mediaPlayer.play();
				couperSonMusique.getStyleClass().remove(mute);
			} else {
				mediaPlayer.setMute(true);
				mediaPlayer.stop();
				couperSonMusique.getStyleClass().add(mute);
			}
		});
		mediaPlayer.setAutoPlay(true);
		btnJouer.setOnKeyPressed(event -> {
			if (jouerSonActif && event.getCode() == KeyCode.TAB) {
				jouerSon("src/Audio/lancerJeu.mp3");
			}
		});

		couperSonMusique.setOnKeyPressed(event -> {
			if (jouerSonActif && event.getCode() == KeyCode.TAB) {
				jouerSon("src/Audio/boutonCouperMusique.mp3");
			}
		});

		closeButton.setOnKeyPressed(event -> {
			if (jouerSonActif && event.getCode() == KeyCode.TAB) {
				jouerSon("src/Audio/boutonQuitter.mp3");
			}
		});
	}
	
	@FXML
	private Button closeButton;
	@FXML
	void close() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

    /**
     * Détermine les touches claviers entrées par l'utilisateur
     * Si la touche correspond à une action alors elle sera exécutée.
     */
	private void currenKeyBinding() {
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.J) {
				lancerXML("PagePersonnage.fxml");
			}
			if (e.getCode() == KeyCode.ESCAPE) {
				Platform.exit();
			}
			if (e.getCode() == KeyCode.P) {
				mediaPlayer.setMute(true);
			}
			if (e.getCode() == KeyCode.O) {
				mediaPlayer.setMute(false);
			}
		});
	}
}
