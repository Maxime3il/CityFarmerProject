package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyEvent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.util.Duration;
public class PageAccueilController implements Initializable {

	@FXML
	private BorderPane scene;

	@FXML
	private MediaView mediaView;
	private MediaPlayer mediaPlayer;
	private Media media;

	private PageJouerController jouerController = new PageJouerController();

	/**
     * Cette fonction permet de lancer une nouvelle fenetre a partir d'un fichier FXML.
     * @param url L'URL du fichier FXML a charger.
     */
	
	public void lancerXML(String url) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();

            stage.setOnCloseRequest(event -> {
                event.consume();
            });

            stage.initStyle(StageStyle.UNDECORATED);

            Scene scene = new Scene(root1, 1920, 1080);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.out.println("Impossible de charger la fenêtre");
        }
    }

	@FXML
	private Button BtnJouer;

	/**
     * Cette fonction permet de rediriger l'utilisateur vers la page jouer.
     * @param event L'evenement declencheur.
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

	/**
	 * Fonction activerDesactiverSon param: 
	 * return: Active ou Desactive la musique en changeant l'image selon le statut du bouton
	 */
	@FXML
	private void activerDesactiverSon(ActionEvent event) {
		jouerSonActif = !jouerSonActif;
		if (!jouerSonActif) {
			// Si le son est desactiver, on ajouter la classe CSS "muted" au bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().add("muted");
		} else {
			// Si le son est activer, on retire la classe CSS "muted" du bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().remove("muted");
		}
		MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
	}

	/**
	* Cette fonction joue un son a partir du chemin de fichier specifier
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
		volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			mediaPlayer.setVolume(newValue.doubleValue());
		});
		mediaPlayer.setOnEndOfMedia(() -> {
			mediaPlayer.seek(Duration.ZERO);
		});
		couperSonMusique.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (mediaPlayer.isMute()) {
					mediaPlayer.setMute(false);
					mediaPlayer.play();
					couperSonMusique.getStyleClass().remove("muted");
				} else {
					mediaPlayer.setMute(true);
					mediaPlayer.stop();
					couperSonMusique.getStyleClass().add("muted");
				}
			}
		});
		mediaPlayer.setAutoPlay(true);
		BtnJouer.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (jouerSonActif && event.getCode() == KeyCode.TAB) {
					jouerSon("src/Audio/lancerJeu.mp3");
				}
			}
		});
		
		couperSonMusique.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (jouerSonActif && event.getCode() == KeyCode.TAB) {
					jouerSon("src/Audio/boutonCouperMusique.mp3");
				}
			}
		});

		closeButton.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (jouerSonActif && event.getCode() == KeyCode.TAB) {
					jouerSon("src/Audio/boutonQuitter.mp3");
				}
			}
		});
	}

	@FXML
	private Button closeButton;
	
	/**
	 * Cette méthode est appelée lorsque l'utilisateur clique sur le bouton Fermer.
	 * Elle ferme la fenêtre en cours.
	 */
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
