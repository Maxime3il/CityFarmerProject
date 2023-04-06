package application;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.animation.PauseTransition;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.input.MouseEvent;

public class PageAccueilController implements Initializable {

	// parametres
	@FXML
	private Button closeButton;
	@FXML
	private Button btnJouer;
	@FXML
	private Slider volumeSlider;
	@FXML
	private Button couperSonMusique;
	@FXML
	private Button btnActiverDesactiverSon;
	@FXML
	private BorderPane scene;
	@FXML
	private MediaView mediaView;
	private boolean firstFocus = true;
	private MediaPlayer mediaPlayer;
	private Media media;
	
	private boolean jouerSonActif = true;
	private PageJouerController jouerController = new PageJouerController();

	// methodes
	
	/*
     * Cette fonction permet de lancer une nouvelle fen�tre � partir d'un fichier FXML.
     * @param url L'URL du fichier FXML � charger.
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
			btnActiverDesactiverSon.getStyleClass().add("muted");
		} else {
			// Si le son est activé, on retire la classe CSS "muted" du bouton
			// btnActiverDesactiverSon
			btnActiverDesactiverSon.getStyleClass().remove("muted");
		}
		MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// on ecoute les touches du clavier et on réagit
		currenKeyBinding();
		// Si le son est actif, on lance le son d'audio description
		if(jouerSonActif) {
			jouerSon("src/Audio/lancementJeu.mp3");
		}
		// on gère la vidéo de fond
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
		// on gere le bouton pour couper la video
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
		
		// on lance la video
		mediaPlayer.setAutoPlay(true);
		
		// on gere le focus des boutons
		couperSonMusique.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				// si le son est actif et que ce n'est pas le premier focus (si je le laisse par défaut, les deux sons se lancent car le focus 
				// est fait directement sur ce champ quand la page charge)
				// le firstFocus me permet de savoir s'il s'agit du premier focus ou non
				if (jouerSonActif && this.firstFocus == false) {
					jouerSon("src/Audio/boutonCouperMusique.mp3");
				}
				// la premiere fois qu'il rentre ici il désactive le firstFocus, le son se lancera à chaque fois. J'évite qu'il se lance lorsque la page est chargée
				this.firstFocus = false;
			}
		});
		
		closeButton.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/boutonQuitter.mp3");
				}
			}
		}); 
		
		btnJouer.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/lancerJeu.mp3");
				}
			}
		}); 
		
		btnActiverDesactiverSon.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue) {
				if (jouerSonActif) {
					jouerSon("src/Audio/boutonAccessibilite.mp3");
				}
			}
		}); 
	}
	
	/*
     * Cette fonction permet de rediriger l'utilisateur vers la page jouer.
     * @param event L'�v�nement d�clencheur.
     */
	
	@FXML
	private void redirectJouer(ActionEvent evt) {
		lancerXML("PagePersonnage.fxml");
	}
	
	/*
	* Cette fonction joue un son � partir du chemin de fichier sp�cifi�.
	* @param path Le chemin de fichier du son � jouer.
	*/
	private void jouerSon(String path) {
		MediaPlayerSingleton.getInstance().jouerSon(path);
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
			if (e.getCode() == KeyCode.A) {
				jouerSonActif = !jouerSonActif;
				if (!jouerSonActif) {
					// Si le son est desactivé, on ajoute la classe CSS "muted" au bouton
					// btnActiverDesactiverSon
					btnActiverDesactiverSon.getStyleClass().add("muted");
				} else {
					// Si le son est activé, on retire la classe CSS "muted" du bouton
					// btnActiverDesactiverSon
					btnActiverDesactiverSon.getStyleClass().remove("muted");
				}
				MediaPlayerSingleton.getInstance().setMute(!jouerSonActif);
			}
		});
	}
}
